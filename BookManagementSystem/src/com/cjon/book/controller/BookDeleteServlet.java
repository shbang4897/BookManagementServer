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
 * Servlet implementation class BookDeleteServlet
 */
@WebServlet("/bookDelete")
public class BookDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public BookDeleteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isbn = request.getParameter("isbn");
		String callback = request.getParameter("callback");
		
		System.out.println("isbn in sevlet :"+isbn);
		
		
		BookService service = new BookService();
		boolean result = service.bookDelete(isbn);
		
		
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
