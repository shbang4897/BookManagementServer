package com.cjon.review.service;

import com.cjon.book.dao.BookDAO;
import com.cjon.review.dao.ReviewDAO;

public class ReviewService {

	public String getList(String id) {
		ReviewDAO dao = new ReviewDAO();
		String result = dao.select(id);	
		
		return result;
	}

	public boolean reviewDelete(String cid) {
		ReviewDAO dao = new ReviewDAO();
		boolean result = dao.delete(cid);	
		return result;
	}

	public String getAllList(String title) {
		ReviewDAO dao = new ReviewDAO();
		String result = dao.selectAll(title);	
		
		return result;
	}

	public boolean insertReview(String isbn, String id, String title, String contents) {
		ReviewDAO dao = new ReviewDAO();
		boolean result = dao.insert(isbn,id,title,contents);	
		return result;
	}

	public String getKeyList(String ctitle) {
		ReviewDAO dao = new ReviewDAO();
		String result = dao.selectKeyAll(ctitle);	
		
		return result;
	}

}
