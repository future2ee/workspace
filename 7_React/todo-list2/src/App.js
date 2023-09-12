import React, { useState, createContext} from 'react';
import './App.css';
import SignupContainer from './signup';
import Login from './Login';
import { TodoListContext } from '../../todo-list/src/App';

export const TodoListContext = createContext();

function App() {
  
  const [signupView, setSignupView] = useState(false);
  
  const [loginMember, setLoginMember] = useState(null);
  
  const [todoList, setTodoList] = useState([]);

  return (
    <TodoListContext.Provider value={ {setTodoList, setLoginMember, loginMember}}>
      <button onClick={()=>{setSignupView(!signupView)}}>
        {signupView ? ('회원 가입 닫기') : ('회원 가입 열기')}
      </button>

      <div className='signup-wrapper'>
        {signupView === true && (<SignupContainer/>)}
      </div>
      <Login/>
    </TodoListContext.Provider>
  );
}

export default App;
