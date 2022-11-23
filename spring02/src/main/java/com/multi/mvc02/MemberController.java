package com.multi.mvc02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	
	@RequestMapping("insert.multi")
	public void insert(MemberDTO bag) {
		System.out.println("클라이언트 회원가입 처리 요청됨.");
	}
	
	@RequestMapping("login.multi")
	public void login(MemberDTO bag) {
		System.out.println("로그인 처리 요청됨.");
		System.out.println(bag);
	}
	
	@RequestMapping("delete.multi")
	public void delete(String id) {
		System.out.println("삭제 처리 요청됨." + id);
		
	}
}
