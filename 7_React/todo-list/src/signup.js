import React, {useState} from 'react';

const SignupContainer = () => {

    const [id, setId] = useState('');
    const [pw, setPw] = useState('');
    const [pwCheck, setPwCheck] = useState('');
    const [name, setName] = useState('');
    const [result, setResult] = useState('');

    // 회원 가입 함수
    const signup = () => {
        // 1. 비밀번호가 일치하지 않으면 (pw !== pwCheck)
        //  '비밀번호가 일치하지 않습니다' alert로 출력 후 return
        if(pw !== pwCheck){
            alert('비밀번호가 일치하지 않습니다.');
            return;
        }

        /** 회원 가입 요청(비동기, POST) */
        fetch("/signup",{
            method : "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            // JS Object -> JSON
            body : JSON.stringify({
                id:id,
                pw:pw,
                name:name
            })
        })
        .then(resp => resp.text())
        .then(result => {
            if(result > 0){
                setResult('회원 가입 성공');
                
                setId('');
                setPw('');
                setPwCheck('');
                setName('');

            }
            else{
                setResult('회원 가입 실패');
    

            }
        })
        .catch( e => console.log(e) );


        // 2. id === 'user01', pw === 'pass01'
        //  맞으면 result에 '회원 가입 성공' 출력
        //  + 모든 입력칸(input) 내용 삭제
        
    };


    return(
        <div className='signup-container'>
            <label>
                ID:
                <input type='text' 
                    onChange={e=>{setId(e.target.value)}}
                    value={id}/>
            </label>

            <label>
                PW:
                <input type='password' 
                    onChange={e=>{setPw(e.target.value)}}
                    value={pw}/>
            </label>
                        
            <label>
                PW CHECK:
                <input type='password' 
                    onChange={e=>{setPwCheck(e.target.value)}}
                    value={pwCheck}/>
            </label>
        
            <label>
                NAME:
                <input type='text' 
                    onChange={e=>{setName(e.target.value)}}
                    value={name}/>
            </label>

            <button onClick={signup}>가입하기</button>            

            <hr/>

            <h3>{result}</h3>
        </div>
    );


};

export default SignupContainer;