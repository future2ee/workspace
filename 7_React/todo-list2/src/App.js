import React, { useState, createContext} from 'react';
import './App.css';
import SignupContainer from './signup';

function App() {

  const [signupView, setSignupView] = useState(false);
  return (
    <>
      <button onClick={()=>{setSignupView(!signupView)}}>
        {signupView ? ('회원 가입 닫기') : ('회원 가입 열기')}
      </button>

      <div className='signup-wrapper'>
        {signupView === true && (<SignupContainer/>)}
      </div>
    </>
  );
}

export default App;
