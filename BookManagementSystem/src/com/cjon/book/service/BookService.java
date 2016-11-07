package com.cjon.book.service;

import com.cjon.book.dao.BookDAO;

public class BookService {

	// 리스트를 가져오는 일을 하는 method
	public String getList(String keyword) {
		// 일반적인 로직처리 나와요!!

		// 추가적으로 DB처리가 나올 수 있어요!
		// DB처리는 Service에서 처리는하는게 아니라..다른 객체를 이용해서
		// Database처리를 하게 되죠!!
		BookDAO dao = new BookDAO();
		String result = dao.select(keyword);

		return result;
	}

	public boolean updateBook(String isbn, String title, String author, String price) {
		// TODO Auto-generated method stub
		BookDAO dao = new BookDAO();
		boolean result = dao.update(isbn, title, author, price);
		return result;
	}

	public boolean insertBook(String isbn, String title, String date, String page, String price, String author,
			String translator, String supplement, String publisher, String callback, String img) {
		BookDAO dao = new BookDAO();
		boolean result = dao.insert(isbn, title, date, page, price, author, translator, supplement, publisher, img);
		return result;
	}

	public boolean bookDelete(String isbn) {
		BookDAO dao = new BookDAO();
		System.out.println("isbn in service :" + isbn);
		boolean result = dao.delete(isbn);
		return result;
	}

	public String bookDetail(String isbn) {
		BookDAO dao = new BookDAO();
		System.out.println("isbn in service :" + isbn);
		String result = dao.detail(isbn);
		return result;
	}

	public boolean signUp(String id, String pass, String email) {
		BookDAO dao = new BookDAO();
		boolean result = dao.signup(id, pass, email);
		return result;
	}

	public boolean login(String id, String pass) {
		BookDAO dao = new BookDAO();
		boolean result = dao.login(id, pass);
		return result;
	}

	public boolean lentBook(String isbn, String id) {
		BookDAO dao = new BookDAO();
		boolean result = dao.lent(isbn, id);
		return result;
	}

	public boolean lentChk(String isbn) {
		BookDAO dao = new BookDAO();
		boolean result = dao.lentChk(isbn);	
		return result;	
	}

	public boolean backBook(String isbn) {
		BookDAO dao = new BookDAO();
		boolean result = dao.backBook(isbn);
		return result;
	}

}
