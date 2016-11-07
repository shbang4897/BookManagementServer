package com.cjon.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjon.book.service.BookService;

@WebServlet("/booklent")
public class BookLentalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BookLentalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		String id = request.getParameter("id");
		String callback = request.getParameter("callback");
		
		System.out.println(isbn);
		System.out.println(id);
		
		// 2. 로직처리
		BookService service = new BookService();
		boolean result = service.lentBook(isbn,id);
		// 3. 출력처리
		response.setContentType("text/plain; charset=utf8");
		PrintWriter out = response.getWriter();
		out.println(callback + "(" + result + ")");
		out.flush();
		out.close();
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
