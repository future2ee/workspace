ALTER SESSION SET"_ORACLE_SCRIPT" = TRUE;




-- 암호화된 비밀번호로 업데이트
UPDATE MEMBER
SET MEMBER_PW = 'aBN5hiegXlvAovJLipPoPd5LB+xjPrAeu1tcAVg0p5MKGocvo6l825SD+ZMCOcHBFeGB7MnzH31SAnDzYYsSdg=='
WHERE MEMBER_EMAIL = 'user01@kh.or.kr'; 

-- MEMBER TABLE 비밀번호 컬럼 크기 변경
ALTER TABLE MEMBER
MODIFY MEMBER_PW VARCHAR2(100);

-- 암호화된 비밀번호로 업데이트
commit;










