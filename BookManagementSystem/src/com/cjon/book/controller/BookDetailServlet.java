package com.cjon.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjon.book.service.BookService;

/**
 * Servlet implementation class BookDetailServlet
 */
@WebServlet("/BookDetail")
public class BookDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BookDetailServlet() {
        super();
 
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		String callback = request.getParameter("callback");
		
		System.out.println("isbn in sevlet :"+isbn);
		
		
		BookService service = new BookService();
		String result = service.bookDetail(isbn);
		
		System.out.println(result);
		response.setContentType("text/plain; charset=utf8");
		PrintWriter out = response.getWriter();
		out.println(callback + "(" + result + ")");
		out.flush();
		out.close();
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
