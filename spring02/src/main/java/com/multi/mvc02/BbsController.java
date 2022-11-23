package com.multi.mvc02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BbsController {

	@RequestMapping("insert2.multi")
	public void insert(BbsDTO bag) {
		System.out.println("컨트롤러 연결 완료");
		BbsDAO dao = new BbsDAO();
		dao.create(bag);
	}
}
