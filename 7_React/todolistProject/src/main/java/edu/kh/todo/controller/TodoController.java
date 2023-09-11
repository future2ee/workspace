package edu.kh.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.todo.model.dto.TodoMember;
import edu.kh.todo.model.service.TodoService;

@RestController
public class TodoController {

	@Autowired
	private TodoService service;

	/** 회원 가입 */
	@PostMapping(value="/signup", produces="application/json; charset=UTF-8" )
	public int signup(@RequestBody TodoMember member) {


		return service.signup(member);
	}

	@GetMapping("/test")
	public int test() {
		return 100;
	}



}
