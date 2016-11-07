package com.cjon.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cjon.book.common.DBTemplate;

public class BookDAO {

	public String select(String keyword) {
		// Database처리가 나와요!
		// 일반적으로 Database처리를 쉽게 하기 위해서
		// Tomcat같은 경우는 DBCP라는걸 제공해 줘요!
		// 추가적으로 간단한 라이브러리를 이용해서 DB처리를 해 볼꺼예요!!
		// 1. Driver Loading ( 설정에 있네.. )
		// 2. Connection 생성
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			String sql = "select bisbn, bimgbase64, btitle, bauthor, bprice, lentperson "
					   + "from book where btitle like ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("isbn", rs.getString("bisbn"));
				obj.put("img", rs.getString("bimgbase64"));
				obj.put("title", rs.getString("btitle"));
				obj.put("author", rs.getString("bauthor"));
				obj.put("price", rs.getString("bprice"));
				obj.put("lentperson", rs.getString("lentperson"));
				
				arr.add(obj);
			}
			result = arr.toJSONString();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public boolean update(String isbn,String title, String author, String price) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		boolean result = false;
		try {
			System.out.println(isbn);
			System.out.println(price);
			String sql = "update book set btitle=?,bauthor =?, bprice=? where bisbn=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, author);
			pstmt.setInt(3, Integer.parseInt(price));
			pstmt.setString(4, isbn);
			
			int count = pstmt.executeUpdate();
			// 결과값은 영향을 받은 레코드의 수
			if( count == 1 ) {
				result = true;
				// 정상처리이기 때문에 commit
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}
			
		} catch (Exception e) {
			System.out.println(e);
			DBTemplate.rollback(con);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public boolean insert(String isbn, String title, String date, String page, String price, String author,
			String translator, String supplement, String publisher, String img) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		System.out.println(img);
		
		boolean result = false;
		try {
			String sql = "insert into book values (?,?,?,?,?,?,?,?,?,'',?,null)";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			pstmt.setString(2, title);
			pstmt.setString(3, date);
			pstmt.setInt(4, Integer.parseInt(page));
			pstmt.setInt(5, Integer.parseInt(price));
			pstmt.setString(6, author);
			pstmt.setString(7, translator);
			pstmt.setString(8, supplement);
			pstmt.setString(9, publisher);
			pstmt.setString(10, img);
			
			
			int count = pstmt.executeUpdate();
			System.out.println(count);
			// 결과값은 영향을 받은 레코드의 수
			if( count == 1 ) {
				result = true;
				// 정상처리이기 때문에 commit
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public boolean delete(String isbn) {
		
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		System.out.println("isbn in dao :"+isbn);
		boolean result = false;
		try {
			String sql = "delete from book where bisbn = ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			
			int count = pstmt.executeUpdate();
			System.out.println(count);
			// 결과값은 영향을 받은 레코드의 수
			if( count == 1 ) {
				result = true;
				// 정상처리이기 때문에 commit
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		
		return result;
	}

	public String detail(String isbn) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		System.out.println("isbn in dao :"+isbn);
		try {
			String sql = "select bdate, bpage, btranslator, bsupplement, bpublisher "
					   + "from book where bisbn = ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, isbn );
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("date", rs.getString("bdate"));
				obj.put("page", rs.getString("bpage"));
				obj.put("translator", rs.getString("btranslator"));
				obj.put("supplement", rs.getString("bsupplement"));
				obj.put("publisher", rs.getString("bpublisher"));
				arr.add(obj);
			}
			
			result = arr.toJSONString();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public boolean signup(String id, String pass, String email) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		
		boolean result = false;
		try {
			String sql = "insert into customer values (?,?,?)";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, email);
			
			
			
			int count = pstmt.executeUpdate();
			System.out.println(count);
			// 결과값은 영향을 받은 레코드의 수
			if( count == 1 ) {
				result = true;
				// 정상처리이기 때문에 commit
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}
			
		} catch (Exception e) {
			System.out.println(e);
			result = false;
			
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		
		System.out.println(result);
		return result;
	}

	public boolean login(String id, String pass) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		String passChk= null;
		try {
			String sql = "select pass from customer where id = ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			System.out.println("id : "+id);
			System.out.println("pass : "+pass);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
			passChk = rs.getString("pass");
			}
			System.out.println("passChk : "+ passChk);
			if(pass.equals(passChk)==true){
				System.out.println("ok");
				result=true;
			}else{
				System.out.println("x");
				result=false;
			}
			System.out.println(result);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public boolean lent(String isbn, String id) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		boolean result = false;
		try {
			System.out.println(isbn);
			System.out.println(id);
			String sql = "update book set lentperson= ? where bisbn=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, isbn);
			
			int count = pstmt.executeUpdate();
			// 결과값은 영향을 받은 레코드의 수
			if( count == 1 ) {
				result = true;
				// 정상처리이기 때문에 commit
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}
			
		} catch (Exception e) {
			System.out.println(e);
			DBTemplate.rollback(con);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public boolean lentChk(String isbn) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		String lentperson= null;
		try {
			String sql = "select lentperson from book where bisbn = ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			System.out.println("id : "+isbn);
			rs = pstmt.executeQuery();
			while(rs.next()){
				lentperson = rs.getString("lentperson");
			}
			
			if(lentperson.equals(null)==true){
				
				result=true;
			}else if (lentperson.equals(null)==false){
				
				System.out.println("lentperson : "+ lentperson);
				result=false;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			DBTemplate.close(rs);
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

	public boolean backBook(String isbn) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		boolean result = false;
		try {
			String sql = "update book set lentperson= null where bisbn=?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			
			int count = pstmt.executeUpdate();
			// 결과값은 영향을 받은 레코드의 수
			if( count == 1 ) {
				result = true;
				// 정상처리이기 때문에 commit
				DBTemplate.commit(con);
			} else {
				DBTemplate.rollback(con);
			}
			
		} catch (Exception e) {
			System.out.println(e);
			DBTemplate.rollback(con);
		} finally {
			DBTemplate.close(pstmt);
			DBTemplate.close(con);
		} 
		return result;
	}

}
















