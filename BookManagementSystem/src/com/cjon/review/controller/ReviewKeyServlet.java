package com.cjon.review.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cjon.review.service.ReviewService;

/**
 * Servlet implementation class ReviewKeyServlet
 */
@WebServlet("/reviewkey")
public class ReviewKeyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReviewKeyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String ctitle = request.getParameter("ctitle");
		String callback = request.getParameter("callback");
		System.out.println("title in servlet :"+ ctitle);
		System.out.println("id in servlet :"+ id);
		ReviewService service = new ReviewService();
		String result = null;
		
		if (id == "") {
			System.out.println("title in if :"+ ctitle);
			result = service.getKeyList(ctitle);
		} else {
			result = service.getList(id);
		}
		System.out.println(result);

		response.setContentType("text/plain; charset=utf8");
		PrintWriter out = response.getWriter();
		out.println(callback + "(" + result + ")");
		out.flush();
		out.close();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
