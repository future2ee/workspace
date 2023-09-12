import React, { useState, createContext } from 'react';
import './App.css';
import SignupContainer from './signup';
import Login from './Login';

export const TodoListContext = createContext(); // 전역변수 생성


function App() {
  // 회원가입, 로그인, 회원의 Todo List 출력/추가/제거
  const [signupView, setSignupView] = useState(false);

  // 로그인한 회원 정보 저장
  const [loginMember, setLoginMember] = useState(null);

  // 로그인한 회원의 todo-list를 저장
  const [todoList, setTodoList] = useState([]);

  return (
    <TodoListContext.Provider value={ {setTodoList, setLoginMember, loginMember} }>
      <button onClick={() => { setSignupView(!signupView) }}>
        {signupView ? ('회원 가입 닫기') : ('회원 가입 열기')}
      </button>

      <div className='signup-wrapper'>
        {/* signupView가 true인 경우에만 회원 가입 컴포넌트 렌더링 */}
        {/* 조건식 && (true인경우) */}
        {signupView === true && (<SignupContainer/>)}
      </div>

      <h1>Todo-list</h1>
      <Login/>
    </TodoListContext.Provider>
  );
}

export default App;