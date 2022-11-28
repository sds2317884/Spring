package com.multi.mvc02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class BookDAO {
	public ArrayList<BookDTO> list() {
		ArrayList<BookDTO> list = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mydb";
			String user = "abcd";
			String password = "12345678";
			Connection con = DriverManager.getConnection(url, user, password); // Connection
			String sql = "select * from book";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(); // r의 결과는 table, cud는 숫자(실행된row수) //1
			while (rs.next()) { // 결과값이 true이면!!
				BookDTO bag = new BookDTO();
				bag.setId(rs.getInt(1));
				bag.setName(rs.getString(2));
				bag.setUrl(rs.getString(3));
				bag.setImg(rs.getString(4));
				list.add(bag);
			} // while
			System.out.println("box(list)에 들어간 가방의 갯수>> " + list.size());
			// db처리와 관련된 메모리 할당된 것 해제시켜주자.
			ps.close();
			con.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 메서드의 처리를 다 끝내고, 결과를 보내줘라는 개념. 맨 끝에 써야함.
		return list;
	} // list
	
	public void insert(BookDTO bag) { // String id
		System.out.println(bag); // toString()호출, 가방에 들어있는 값들이 프린트
		// 자바에서 DBMS를 연결하려고 함.
		// JDBC 프로그래밍 절차
		// 4단계 절차
		// 1. 드라이버 설정- 드라이버(커넥터) 로딩
		// db의 유형, 버전에 따라 매우 많음.
		// 필요할 때 다운로드 받아 써야 함.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 특정한 위치에 있는 드라이버 파일을 램에 읽어들여 설정.
			System.out.println("1. 드라이버 설정 성공.@@@@");
			// 2. db연결 mySQL: school, oracle: xe
			String url = "jdbc:mysql://localhost:3306/mydb";
			String user = "abcd";
			String password = "12345678";
			Connection con = DriverManager.getConnection(url, user, password); // Connection
			System.out.println("2. db연결 성공.@@@@@@");
			// 3. sql문을 만든다.
			String sql = "insert into book values (null, ?, ?, ?)";
			// 준비된 문장(Preparedstatement)
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bag.getName()); // 2번 물음표에 pw에 저장해놓은 값을 스트링으로 넣어줘!
			ps.setString(2, bag.getUrl()); // 3번 물음표에 name에 저장해놓은 값을 스트링으로 넣어줘!
			ps.setString(3, bag.getImg()); // 4번 물음표에 tel에 저장해놓은 값을 스트링으로 넣어줘!
			System.out.println("3. sql문 생성 성공.@@@@@");
			// String site = "http://www.naver.com";
			// Url url = new Url(site); //객체화!!
			// 4. sql문을 db서버에 보낸다. --> 결과가 어떻게 되었는지 자바프로그램 알려줌.
			String result2 = "실패";
			try {
				int result = ps.executeUpdate(); // r의 결과는 table, cud는 숫자(실행된row수) //1
				System.out.println("4. sql문 db서버로 전송 성공. @@@@@ 반영된 row수 >>" + result);
				if (result == 1) {
					result2 = "성공";
				}
			} catch (Exception e) {
				System.out.println("회원가입시 에러발생");
			}
			// JOptionPane.showMessageDialog(null, result2); //컴파일 에러.
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // insert
}
