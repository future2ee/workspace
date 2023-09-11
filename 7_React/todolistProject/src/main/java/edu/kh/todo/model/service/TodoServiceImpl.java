package edu.kh.todo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.todo.model.dao.TodoDAO;
import edu.kh.todo.model.dto.TodoMember;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoDAO dao;

	@Override
	public int signup(TodoMember member) {

		return dao.signup(member);
	}

}
