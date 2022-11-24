package com.multi.mvc02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
	@Autowired
	BookDAO dao;
	
	@RequestMapping("insert3.multi")
	public void insert(BookDTO bag) {
		System.out.println("컨트롤러 연결 완료");
		dao.insert(bag);
	}
}
