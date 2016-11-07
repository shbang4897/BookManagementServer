package com.cjon.review.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjon.book.service.BookService;
import com.cjon.review.service.ReviewService;

/**
 * Servlet implementation class reviewDelServlet
 */
@WebServlet("/reviewDelete")
public class reviewDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public reviewDelServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		String callback = request.getParameter("callback");

		ReviewService service = new ReviewService();
		boolean result = service.reviewDelete(cid);
		
		
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
