/* VIEW

    - SELECT문의 실행 결과 (RESULT SET)를 저장하는 객체
    - 논리적 가상 테이블
        -> 테이블 모양을 하고는 있지만 실제로 값을 저장하고 있진 않음
    
    ** VIEW 사용 목적
    1) 복잡한 SELECT 문을 쉽게 재사용하기 위해 사용
    2) 테이블의 진짜 모습을 감출 수 있어 보안상 유리하다.
    
    *** VIEW 사용 시 주의사항 ***
    1) 가상의 테이블(실체 X)이기 때문에 ALTER 구문 사용 불가능 
    2) VIEW를 이용한 DML(INSERT, UPDATE, DELETE)가 가능한 경우도 있지만
       제약이 많이 따르기 때문에 보통은 조회(SELECT)용도로 많이 사용함
    

    [VIEW 생성 방법]
    CREATE [OR REPLACE] [FORCE | NOFORCE] VIEW 뷰이름 [(alias[,alias]...]
    AS subquery
    [WITH CHECK OPTION]
    [WITH READ ONLY];
    
    --1) OR REPLACE 옵션 : 기존에 동일한 뷰 이름이 존재하는 경우 덮어쓰고, 
                          존재하지 않으면 새로 생성
                          
    --2) FORCE / NOFORCE 옵션
    --   FORCE : 서브쿼리에 사용된 테이블이 존재하지 않아도 뷰 생성
    --   NOFORCE : 서브쿼리에 사용된 테이블이 존재해야만 뷰 생성(기본값)
    
    --3) WITH CHEK OPTION 옵션  : 옵션을 설정한 컬럼의 값을 수정 불가능하게 함
    --4) WITH READ ONLY 옵션 : 뷰에 대한 조회만 가능( DML 수행 불가 )

*/

--EMPLOYEE 테이블에서 
-- 모든 사원의 사번, 이름, 부서명, 직급면 조회
SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME
FROM EMPLOYEE
LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
JOIN JOB USING(JOB_CODE);
--> 자주 사용하는데 매번 쓰기 힘들다 ...-> VIEW 생성

-- 사번, 이름, 부서명, 직급명 VIEW 생성
CREATE VIEW V_EMP
AS SELECT EMP_ID, EMP_NAME, DEPT_TITLE, JOB_NAME
    FROM EMPLOYEE
    LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
    JOIN JOB USING(JOB_CODE);
--ORA-01031: 권한이 불충분합니다
--View V_EMP이(가) 생성되었습니다.

--lmr 계정에 VIEW 생성 권한을 부여
-- (실행 시 관리자 계정으로 할 것)
GRANT CREATE VIEW TO lmr; --Grant을(를) 성공했습니다.
--> 다시 이니셜 계정으로 접속 -> 뷰 생성 구문 다시 실행

--VIEW를 이용한 조회
SELECT * FROM V_EMP;

-------------------------------------------------------------------------------

-- ** OR REPLACE + 별칭 **
CREATE OR REPLACE VIEW V_EMP
AS SELECT EMP_ID 사번, EMP_NAME 이름, DEPT_TITLE 부서명, JOB_NAME 직급명
    FROM EMPLOYEE
    LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID)
    JOIN JOB USING(JOB_CODE);
-- ORA-00955: 기존의 객체가 이름을 사용하고 있습니다.
--> OR REPLACE 옵션 사용

-- 별칭이 적용된 VIEW에서 직급이 '대리'인 직원만 조회
    --> VIEW에서 조회된 컬럼의 이름을 조건절에 사용해야 한다!
SELECT * FROM V_EMP
WHERE 직급명 = '대리';

-------------------------------------------------------------------------------

-- * VIEW를 이용한 DML 확인 *

--테이블 복사
CREATE TABLE DEPT_COPY2
AS SELECT * FROM DEPARTMENT;

SELECT * FROM DEPT_COPY2;

-- 복사한 테이블을 이용해서 VIEW 생성
CREATE OR REPLACE VIEW V_DCOPY2
AS SELECT DEPT_ID, LOCATION_ID FROM DEPT_COPY2;

--뷰 생성 확인
SELECT * FROM V_DCOPY2;

-- 뷰를 이용한 INSERT
INSERT INTO V_DCOPY2 VALUES('D0','L3'); --1 행 이(가) 삽입되었습니다.

--삽입 확인
SELECT * FROM V_DCOPY2; --> VIEW에 'D0'가 삽입된걸 확인함
--> 가상의 테이블인 VIEW에 데이터 삽입이 가능한걸까? NO

-- 원본 테이블 확인
SELECT * FROM DEPT_COPY2; --'D0',NULL,'L3'
--> VIEW에 삽입한 내욜이 원본 테이블에 존재함
--> VIEW를 이용한 DML 구문이 원본에 영향을 미친다.

--VIEW를 이용한 DML시 발생하는 문제점 == 제약조검 위배 현상
ROLLBACK;
SELECT * FROM DEPT_COPY2;
SELECT * FROM V_DCOPY2;

-- 원본 테이블 DEPT_TITLE 컬럼에 NOT NULL 제약조건 추가
ALTER TABLE DEPT_COPY2
MODIFY DEPT_TITLE NOT NULL;

-- 현 상태에서 다시 VIEW를 이용한 INSERT 수행
INSERT INTO V_DCOPY2 VALUES('D0','L3');
-- ORA-01400: NULL을 ("LMR"."DEPT_COPY2"."DEPT_TITLE") 안에 삽입할 수 없습니다

--> VIEW를 이용한 INSERT시 원본 테이블에 삽입이 된다.
--> 원본 테이블 삽입 시 VIEW INSERT 구문이 미포함된 컬럼에는 NULL이 저장되다.
--> 그런데 DEPT_TITLE 컬럼에 NOT NULL 제약조건이 설정되어 있음
--> 오류 발생! -> VIEW를 이용한 INSERT 실패

--결론 : VIEW 가지고 DML 웬만하면 하지마세요..

-------------------------------------------------------------------------------

-- * WITH READ ONLY 옵션 *

CREATE OR REPLACE VIEW V_DCOPY2
AS SELECT DEPT_ID, LOCATION_ID FROM DEPT_COPY2
WITH READ ONLY; -- 읽기 전용의 VIEW 생성( DML X)

INSERT INTO V_DCOPY2 VALUES('D0''L1');
-- ORA-42300 :
-------------------------------------------------------------------------------

/* SEQUENCE(순서, 연속)
 - 순차적 번호 자동 발생기 역할의 객체
  EX) 1 2 3 4 5 6 7 8 9 ...
  
  *** SEQUENCE를 사용하는 방법  : PRIMARY KEW 컬럼에 사용될 값을 생성하는 용ㅇ도로 사용 ***
  
   [작성법]
  CREATE SEQUENCE 시퀀스이름
  [STRAT WITH 숫자] -- 처음 발생시킬 시작값 지정, 생략하면 자동 1이 기본
  [INCREMENT BY 숫자] -- 다음 값에 대한 증가치, 생략하면 자동 1이 기본
  [MAXVALUE 숫자 | NOMAXVALUE] -- 발생시킬 최대값 지정 (10의 27승 -1)
  [MINVALUE 숫자 | NOMINVALUE] -- 최소값 지정 (-10의 26승)
  [CYCLE | NOCYCLE] -- 값 순환 여부 지정
  [CACHE 바이트크기 | NOCACHE] -- 캐쉬메모리 기본값은 20바이트, 최소값은 2바이트

--시퀀스의 캐시 메모리는 할당된 크기만큼 미리 다음 값들을 생성해 저장해둠
-- --> 시퀀스 호출 시 미리 저장되어진 값들을 가져와 반환하므로
--     매번 시퀀스를 생성해서 반환하는 것보다 DB속도다 향상됨


    ** 시퀀스 사용 방법 **
    1) 시퀀스명.NEXTVAL : 다음 시퀀스 번호를 얻어온다. (INCREMENT BY만큼 증가된 값)
                         단, 시퀀스 생성 후 첫 호출인 경우 START WITH의 값을 얻어옴
    2) 시퀀스명.CURRVAL : 현재 시퀀스 번호 얻어옴
                         단, 시퀀스 생성 후 NEXTVAL 호출없이 CURRVAL를 호출하면 오류 발생                        

*/

-- 테이블 복사
CREATE TABLE EMPLOYEE_COPY4
AS SELECT EMP_ID, EMP_NAME FROM EMPLOYEE;

--EMPLOYEE_COPY4의 EMP_ID 컬럼에 PK 제약조건 추가
ALTER TABLE EMPLOYEE_COPY4 ADD PRIMARY KEY(EMP_ID);

SELECT * FROM EMPLOYEE_COPY4;

-- 223 부터 시작하여 5씩 증가하는 시퀀스 생성
CREATE SEQUENCE SRQ_EMP_ID
START WITH 223
INCREMENT BY 5;
-- Sequence SRQ_EMP_ID이(가) 생성되었습니다.

--NEXTVAL 호출없이 CURRVAL 호출을 하면 발생하는 오류 확인
SELECT SRQ_EMP_ID.CURRVAL FROM DUAL;
-- ORA-08002: 시퀀스 SRQ_EMP_ID.CURRVAL은 이 세션에서는 정의 되어 있지 않습니다

--NEXTVAL 호출1
SELECT SRQ_EMP_ID.NEXTVAL FROM DUAL; --최초 호출 == START WUTH 값 조회
--223

-- NEXTVAL 호출 2
SELECT SRQ_EMP_ID.NEXTVAL FROM DUAL; --INCREMENT BY 만큼 증가한 값
--228

--CURRVAL 호출
SELECT SRQ_EMP_ID.CURRVAL FROM DUAL; -- NEXTVAL 호출 후 CURRVAL 호출 시 오류 없음

-- 실제사용
SELECT * FROM EMPLOYEE_COPY4;

INSERT INTO EMPLOYEE_COPY4 VALUES (SRQ_EMP_ID.NEXTVAL,'홍길동');
INSERT INTO EMPLOYEE_COPY4 VALUES (SRQ_EMP_ID.NEXTVAL,'김길동');
INSERT INTO EMPLOYEE_COPY4 VALUES (SRQ_EMP_ID.NEXTVAL,'박길동');

SELECT * FROM EMPLOYEE_COPY4;

SELECT SRQ_EMP_ID.CURRVAL FROM DUAL;

-- 시퀀스는 롤백을 수행한다고 해서 돌아가지 않음
--> 계속 증가된 상태를 유지
ROLLBACK;
SELECT * FROM EMPLOYEE_COPY4;
SELECT SRQ_EMP_ID.CURRVAL FROM DUAL;

-------------------------------------------------------------------------------

-- SEQUENCE 변경 (ALTER)
/*
  [작성법]
  ALTER SEQUENCE 시퀀스이름
  [INCREMENT BY 숫자] -- 다음 값에 대한 증가치, 생략하면 자동 1이 기본
  [MAXVALUE 숫자 | NOMAXVALUE] -- 발생시킬 최대값 지정 (10의 27승 -1)
  [MINVALUE 숫자 | NOMINVALUE] -- 최소값 지정 (-10의 26승)
  [CYCLE | NOCYCLE] -- 값 순환 여부 지정
  [CACHE 바이트크기 | NOCACHE] -- 캐쉬메모리 기본값은 20바이트, 최소값은 2바이트
  
  --> 시퀀스 변경 시 시작 번호는 변경할 수 없음
  
  * 시퀀스를 잘못 다루어 번호가 공백이 있어서 다시 처음부터 시작하고 싶은 경우
  --> 시퀀스 삭제 후 재생성
*/

-- SRQ_EMP_ID의 증가값을 5 -> 1로 변경
ALTER SEQUENCE SRQ_EMP_ID
INCREMENT BY 1;
-- Sequence SRQ_EMP_ID이(가) 변경되었습니다.

SELECT SRQ_EMP_ID.NEXTVAL FROM DUAL;

--SEQUENCE 삭제
DROP SEQUENCE SRQ_EMP_ID;

--VIEW 삭제
DROP VIEW V_DCOPY2;

-------------------------------------------------------------------------------

/* INDEX(색인)

--SQL 명령문 중 SELECT의 처리 속도를 향항시키기 위해
컬럼에 대해서 생성하는 거래
 
 인덱스 내부 구조는 'B'트리 형식을로되어이씀
* 인덱스 장점
- 이진 트리 형식으로 구성되어 있어 자동 정렬 및 검색 속도가 빠름
- 조회시 전체 테이블 내용을 저노히하는 것이 아닌 
    인덱스가 지정된 컬럼만을 이용해서 조죄하기 때문에
    시스템 부화가 낮아져 전체적인 성능이 향상된다.
    
    * 인덱스 단점
    - 데이터 변경 (UPDATE,K INSERT, DLEETE)작업 빈번한 경우 오히려 성능이 저하되는 문제가 발생
    - 인덱스도 하나의 객체이다보니 이를 저장하기 위한 별도 공감
    - 인덱스 생성 시간이 필요

--인덱스 생성 방법--

    [작성법]
    CREATE [UNIQUE] INDEX 인덱스명
    ON 테이블명 (컬럼명, 컬럼명, ... | 함수명, 함수계산식);
    
    -- 인덱스가 자동으로 생성되는 경우
    --> PK 또는 UNIQUE 제약조건이 설정되는 경우

*/

SELECT * FROM EMPLOYEE_COPY4;

--EMPLOYEE_COPY4 테이블의 EMP_NAME 컬럼에 INDEX 생성
CREATE INDEX ECOPY4_NAME_IDX
ON EMPLOYEE_COPY4(EMP_NAME);
-- Index ECOPY4_NAME_IDX이(가) 생성되었습니다.

SELECT * FROM EMPLOYEE_COPY4;
--> 인덱스를 사용하지 않은 검색

-- *** 인덱스를 이용한 조회(검색) 방법 ***
--> WHERE절에 인덱스가 추가된 컬럼이 언급되면 자동으로 INDEX가 활용됨
SELECT * FROM EMPLOYEE_COPY4
WHERE EMP_NAME != '0';
--> 데이터가 너무 적어서 차이가 거의 없음..

-- 인덱스 확인용 테이블 생성
CREATE TABLE TB_IDX_TEST(
    TEST_NO NUMBER PRIMARY KEY, -- 자동으로 인덱스가 생성됨
    TEST_ID VARCHAR2(20) NOT NULL
);

DROP TABLE TB_IDX_TEST;
-- TB_IDX_TEST 테이블에 샘플데이터 100만개 삽입 (PL/SQL 사용)
BEGIN
    FOR I IN 1..1000000
    LOOP
        INSERT INTO TB_IDX_TEST VALUES( I , 'TEST' || I );
    END LOOP;
    
    COMMIT;
END;
/

SELECT COUNT (*) FROM TB_IDX_TEST;

-- 인덱스 사용 X
SELECT * FROM TB_IDX_TEST
WHERE TEST_ID = 'TEST500000';--0.015

-- 인덱스 사용 O
SELECT * FROM TB_IDX_TEST
WHERE TEST_NO = 500000; --0.002초

-- 테스트용 테이블 삭제
DROP TABLE TB_IDX_TEST;

--인덱스 삭제
DROP INDEX ECOPY4_NAME_IDX;















