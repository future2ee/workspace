package edu.kh.todo.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.todo.model.dto.TodoMember;

@Repository
public class TodoDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public int signup(TodoMember member) {
		return sqlSession.insert("todoMapper.signup", member);
	}


}
