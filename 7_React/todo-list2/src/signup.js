import React, { useState } from 'react';

const SignupContainer = ()=>{

    const [id, setId] = useState('');
    const [pw, setPw] = useState('');
    const [pwCheck, setPwCheck] = useState('');
    const [name, setName] = useState('');
    const [result, setResult] = useState('');

    const signup =() =>{

        if(pw !== pwCheck){
            alert('비밀번호가 일치하지 않습니다')
            return;
        }
        
        fetch("/signup",{
            method : "POST",
            headers : {
                "Content-Type" : "application/json"
            },
            body : JSON.stringify({
                id:id,
                pw:pw,
                name : name
            })
        })
        .then(resp =>resp.text())
        .then(result =>{
            if(result>0){
                setResult('회원 가입 성공');

                setId('');
                setPw('');
                setPwCheck('');
                setName('');
            }
            else{
                setResult("회원 가입 실패");
            }
        })
        .catch(e=>console.log(e));

    }


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

}


export default SignupContainer;