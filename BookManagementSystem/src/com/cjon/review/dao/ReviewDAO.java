package com.cjon.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cjon.book.common.DBTemplate;

public class ReviewDAO {

	public String select(String id) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			String sql = "select c.cid, b.btitle, c.ctitle, c.cauthor, c.ctext "
					   + "from book b , book_comment c where b.bisbn = c.bisbn and  c.cauthor = ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, id );
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("cid", rs.getString("c.cid"));
				obj.put("btitle", rs.getString("b.btitle"));
				obj.put("ctitle", rs.getString("c.ctitle"));
				obj.put("cauthor", rs.getString("c.cauthor"));
				obj.put("ctext", rs.getString("c.ctext"));
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

	public boolean delete(String cid) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		System.out.println("isbn in dao :"+cid);
		boolean result = false;
		try {
			String sql = "delete from book_comment where cid = ?";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, cid);
			
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

	public String selectAll(String title) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			String sql = "select c.cid, b.btitle, c.ctitle, c.cauthor, c.ctext "
					   + "from book b , book_comment c where b.bisbn = c.bisbn and b.btitle like ?";
			System.out.println("title in dao :"+ title);
			pstmt= con.prepareStatement(sql);
			
			pstmt.setString(1, "%"+title+"%");
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("cid", rs.getString("c.cid"));
				obj.put("btitle", rs.getString("b.btitle"));
				obj.put("ctitle", rs.getString("c.ctitle"));
				obj.put("cauthor", rs.getString("c.cauthor"));
				obj.put("ctext", rs.getString("c.ctext"));
				arr.add(obj);
			}
			result = arr.toJSONString();
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

	public boolean insert(String isbn, String id, String title, String contents) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		
		boolean result = false;
		try {
			String sql = "insert into book_comment values (cid,?,?,?,now(),?)";
			pstmt= con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			pstmt.setString(2, title);
			pstmt.setString(3, id);
			pstmt.setString(4, contents);
			
			
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

	public String selectKeyAll(String ctitle) {
		Connection con = DBTemplate.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = null;
		try {
			String sql = "select c.cid, b.btitle, c.ctitle, c.cauthor, c.ctext "
					   + "from book b , book_comment c where b.bisbn = c.bisbn and c.ctitle like ?";
			System.out.println("title in dao :"+ ctitle);
			pstmt= con.prepareStatement(sql);
			
			pstmt.setString(1, "%"+ctitle+"%");
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			while(rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("cid", rs.getString("c.cid"));
				obj.put("btitle", rs.getString("b.btitle"));
				obj.put("ctitle", rs.getString("c.ctitle"));
				obj.put("cauthor", rs.getString("c.cauthor"));
				obj.put("ctext", rs.getString("c.ctext"));
				arr.add(obj);
			}
			result = arr.toJSONString();
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

}
