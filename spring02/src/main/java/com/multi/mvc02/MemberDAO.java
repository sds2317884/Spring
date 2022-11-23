package com.multi.mvc02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

@Component //싱글톤으로 만들어서 준비해줘 스프링!!
public class MemberDAO {
	public ArrayList<MemberDTO> list() {
		// vo를 넣어줄 커다란 박스역할을 할 수 부품을 사용 예정
		// 가변길이인 경우 arraylist
		// MemberDTO가 많이 들어간 arraylist박스를 하나 만들어줌.
		ArrayList<MemberDTO> list = new ArrayList<>();
		// 1. 드라이버 설정- 드라이버(커넥터) 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 특정한 위치에 있는 드라이버 파일을 램에 읽어들여 설정
			System.out.println("1. 드라이버 설정 성공.@@@@");
			// 2. db연결 mySQL: school, oracle: xe
			String url = "jdbc:mysql://localhost:3306/mydb";
			String user = "abcd";
			String password = "12345678";
			Connection con = DriverManager.getConnection(url, user, password); // Connection
			System.out.println("2. db연결 성공.@@@@@@");
			// 3. sql문을 만든다.
			String sql = "select * from member";
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println("3. sql문 생성 성공.@@@@@");
			// 4. sql문을 db서버에 보낸다. --> 결과가 어떻게 되었는지 자바프로그램 알려줌.
			ResultSet rs = ps.executeQuery(); // r의 결과는 table, cud는 숫자(실행된row수) //1
			// rs.next() true면 결과가 있음. false면 결과가 없음.
			// System.out.println("4. sql문 db서버로 전송 성공. @@@@@ 반영된 row수 >>" + rs.next());
			while (rs.next()) { // 결과값이 true이면!!
				System.out.println("검색결과가 있음.");
				// 있으면
				// 1. 한 행의 값들을 추출
				// 2. vo를 만들어준다.
				// 3. vo에 추출한 값들을 넣어준다.
				// 4. vo를 list에 넣어준다.
				String id2 = rs.getString(1); // 컬럼의 순서 1번 id값 추출
				String pw = rs.getString("pw");
				String name = rs.getString(3);
				String tel = rs.getString(4);
				System.out.println("검색한 결과>>" + id2 + " " + pw + " " + name + " " + tel);
				// UI에서 사용자가 검색한 결과를 볼 수 있어야 한다.
				// 4개의 데이터를 UI에게 return!!해주자.
				// 1) 가방을 만들어서,
				MemberDTO bag = new MemberDTO();
				// 2) 데이터를 넣고,
				bag.setId(id2);
				bag.setPw(pw);
				bag.setName(name);
				bag.setTel(tel);
				list.add(bag);
				// 3) return하자.(전달)
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
	// 기능하나만 메서드 하나 구현(검색 1명 기능-row 1개)
	public int login(MemberDTO bag) {
		// 전달된 값 제대로 전달되었는지 확인하는 습관이 좋음.
		System.out.println(bag);
		// 1. 드라이버 설정- 드라이버(커넥터) 로딩
		// db의 유형, 버전에 따라 매우 많음.
		// 필요할 때 다운로드 받아 써야 함.
		int result = 0; // 0인 경우, 실패라고 하자.!
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 특정한 위치에 있는 드라이버 파일을 램에 읽어들여 설정
			System.out.println("1. 드라이버 설정 성공.@@@@");
			// 2. db연결 mySQL: school, oracle: xe
			String url = "jdbc:mysql://localhost:3306/mydb";
			String user = "abcd";
			String password = "12345678";
			Connection con = DriverManager.getConnection(url, user, password); // Connection
			System.out.println("2. db연결 성공.@@@@@@");
			// 3. sql문을 만든다.
			String sql = "select * from member where id = ? and pw = ?"; // 1, 2번 2개의 물음표 번호
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bag.getId()); // 1번 물음표에 id에 저장해놓은 값을 스트링으로 넣어줘!
			ps.setString(2, bag.getPw());
			System.out.println("3. sql문 생성 성공.@@@@@");
			// 4. sql문을 db서버에 보낸다. --> 결과가 어떻게 되었는지 자바프로그램 알려줌.
			ResultSet rs = ps.executeQuery(); // r의 결과는 table, cud는 숫자(실행된row수) //1
			// rs.next() true면 결과가 있음. false면 결과가 없음.
			// System.out.println("4. sql문 db서버로 전송 성공. @@@@@ 반영된 row수 >>" + rs.next());
			if (rs.next()) { // 결과값이 true이면!!
				System.out.println("검색결과가 있음.");
				result = 1;
				// 3) return하자.(전달)
			}
			// db처리와 관련된 메모리 할당된 것 해제시켜주자.
			ps.close();
			con.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 메서드의 처리를 다 끝내고, 결과를 보내줘라는 개념. 맨 끝에 써야함.
		return result;
	} // login
	
	// 기능하나만 메서드 하나 구현(검색 1명 기능-row 1개)
	public MemberDTO one(String id) {
		// 전달된 값 제대로 전달되었는지 확인하는 습관이 좋음.
		System.out.println(id);
		// 1. 드라이버 설정- 드라이버(커넥터) 로딩
		// db의 유형, 버전에 따라 매우 많음.
		// 필요할 때 다운로드 받아 써야 함.
		MemberDTO bag = null; // 쓰레기값!!--> 변수 초기화시켜주어야함.!
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 특정한 위치에 있는 드라이버 파일을 램에 읽어들여 설정
			System.out.println("1. 드라이버 설정 성공.@@@@");
			// 2. db연결 mySQL: school, oracle: xe
			String url = "jdbc:mysql://localhost:3306/mydb";
			String user = "abcd";
			String password = "12345678";
			Connection con = DriverManager.getConnection(url, user, password); // Connection
			System.out.println("2. db연결 성공.@@@@@@");
			// 3. sql문을 만든다.
			String sql = "select * from member where id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id); // 1번 물음표에 id에 저장해놓은 값을 스트링으로 넣어줘!
			System.out.println("3. sql문 생성 성공.@@@@@");
			// String site = "http://www.naver.com";
			// Url url = new Url(site); //객체화!!
			// 4. sql문을 db서버에 보낸다. --> 결과가 어떻게 되었는지 자바프로그램 알려줌.
			ResultSet rs = ps.executeQuery(); // r의 결과는 table, cud는 숫자(실행된row수) //1
			// rs.next() true면 결과가 있음. false면 결과가 없음.
			// System.out.println("4. sql문 db서버로 전송 성공. @@@@@ 반영된 row수 >>" + rs.next());
			if (rs.next()) { // 결과값이 true이면!!
				System.out.println("검색결과가 있음.");
				String id2 = rs.getString(1); // 컬럼의 순서 1번 id값 추출
				String pw = rs.getString("pw");
				String name = rs.getString(3);
				String tel = rs.getString(4);
				System.out.println("검색한 결과>>" + id2 + " " + pw + " " + name + " " + tel);
				// UI에서 사용자가 검색한 결과를 볼 수 있어야 한다.
				// 4개의 데이터를 UI에게 return!!해주자.
				// 1) 가방을 만들어서,
				bag = new MemberDTO();
				// 2) 데이터를 넣고,
				bag.setId(id2);
				bag.setPw(pw);
				bag.setName(name);
				bag.setTel(tel);
				// 3) return하자.(전달)
			}
			// db처리와 관련된 메모리 할당된 것 해제시켜주자.
			ps.close();
			con.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 메서드의 처리를 다 끝내고, 결과를 보내줘라는 개념. 맨 끝에 써야함.
		return bag;
	} // one
	// 기능하나만 메서드 하나 구현(등록기능)
	public void insert(MemberDTO bag) { // String id
		System.out.println(bag); // toString()호출, 가방에 들어있는 값들이 프린트
		// 자바에서 DBMS를 연결하려고 함.
		// JDBC 프로그래밍 절차
		// 4단계 절차
		// 1. 드라이버 설정- 드라이버(커넥터) 로딩
		// db의 유형, 버전에 따라 매우 많음.
		// 필요할 때 다운로드 받아 써야 함.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 특정한 위치에 있는 드라이버 파일을 램에 읽어들여 설정
			System.out.println("1. 드라이버 설정 성공.@@@@");
			// 2. db연결 mySQL: school, oracle: xe
			String url = "jdbc:mysql://localhost:3306/mydb";
			String user = "abcd";
			String password = "12345678";
			Connection con = DriverManager.getConnection(url, user, password); // Connection
			System.out.println("2. db연결 성공.@@@@@@");
			// 3. sql문을 만든다.
			String sql = "insert into member values (?, ?, ?, ?)";
			// 준비된 문장(Preparedstatement)
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, bag.getId()); // 1번 물음표에 id에 저장해놓은 값을 스트링으로 넣어줘!
			ps.setString(2, bag.getPw()); // 2번 물음표에 pw에 저장해놓은 값을 스트링으로 넣어줘!
			ps.setString(3, bag.getName()); // 3번 물음표에 name에 저장해놓은 값을 스트링으로 넣어줘!
			ps.setString(4, bag.getTel()); // 4번 물음표에 tel에 저장해놓은 값을 스트링으로 넣어줘!
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
			JOptionPane.showMessageDialog(null, result2);  //컴파일 에러
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // insert
	// 기능하나만 메서드 하나 구현(탈퇴기능)
	public void delete(String id) {
		// 자바에서 DBMS를 연결하려고 함.
		// JDBC 프로그래밍 절차
		// 4단계 절차
		// 1. 드라이버 설정- 드라이버(커넥터) 로딩
		// db의 유형, 버전에 따라 매우 많음.
		// 필요할 때 다운로드 받아 써야 함.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 특정한 위치에 있는 드라이버 파일을 램에 읽어들여 설정
			System.out.println("1. 드라이버 설정 성공.@@@@");
			// 2. db연결 mySQL: school, oracle: xe
			String url = "jdbc:mysql://localhost:3306/mydb";
			String user = "abcd";
			String password = "12345678";
			Connection con = DriverManager.getConnection(url, user, password); // Connection
			System.out.println("2. db연결 성공.@@@@@@");
			// 3. sql문을 만든다.
			String sql = "delete from member where id = ?";
			// 준비된 문장(Preparedstatement)
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id); // 1번 물음표에 id에 저장해놓은 값을 스트링으로 넣어줘!
			System.out.println("3. sql문 생성 성공.@@@@@");
			// String site = "http://www.naver.com";
			// Url url = new Url(site); //객체화!!
			// 4. sql문을 db서버에 보낸다. --> 결과가 어떻게 되었는지 자바프로그램 알려줌.
			int result = ps.executeUpdate(); // r의 결과는 table, cud는 숫자(실행된row수) //1
			System.out.println("4. sql문 db서버로 전송 성공. @@@@@ 반영된 row수 >>" + result);
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // delete
} // class