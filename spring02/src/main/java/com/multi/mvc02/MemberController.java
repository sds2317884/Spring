package com.multi.mvc02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	
	@Autowired //이미 만들어서 준비된 MemberDAO객체의 주소를 찾아서 자동으로
				// 그 주소를 아래의 변수인 dao에 넣어주세요.
	MemberDAO dao;
	
	@RequestMapping("insert.multi")
	public void insert(MemberDTO bag, MemberDAO dao) {
		System.out.println("클라이언트 회원가입 처리 요청됨.");
		
		dao.insert(bag);
	}
	
	//로그인처리.
	@RequestMapping("login.multi")
	public String login(MemberDTO bag) {
		System.out.println("로그인 처리 요청됨.");
		System.out.println(bag);
		System.out.println(dao);
		
		int result = dao.login(bag);
		
		if (result==1) {
			return "ok";
		} else {
			return "redirect:member.jsp";
		}
	}
	
	public String loginOld2(MemberDTO bag, MemberDAO dao) {
		System.out.println("로그인 처리 요청됨.");
		System.out.println(bag);
		
		int result = dao.login(bag);
		
		if (result==1) {
			return "ok";
		} else {
			return "redirect:member.jsp";
		}
	}
	
	@RequestMapping("delete.multi")
	public void delete(String id) {
		System.out.println("삭제 처리 요청됨." + id);
		int result = dao.delete(id);
		
		if(result==1) {
			
		}else {
			
		}
		
		
	}
}
