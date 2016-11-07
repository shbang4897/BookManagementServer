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

@WebServlet("/reviewinsert")
public class ReviewInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		String callback = request.getParameter("callback");
	

		
		// 2. 로직처리
		ReviewService service = new ReviewService();
		boolean result = service.insertReview(isbn,id,title,contents);
		// 3. 출력처리
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
