package com.multi.mvc02;

public class BbsDAO {
	
	public void create(BbsDTO bag) {
		System.out.println("가방으로 전달받은 id :" + bag.getId());
		System.out.println("가방으로 전달받은 title :" + bag.getTitle());
		System.out.println("가방으로 전달받은 content :" + bag.getContent());
		System.out.println("가방으로 전달받은 writer :" + bag.getWriter());
	}
}
