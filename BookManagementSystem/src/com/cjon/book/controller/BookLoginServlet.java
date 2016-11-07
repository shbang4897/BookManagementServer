package com.cjon.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.cjon.book.service.BookService;

/**
 * Servlet implementation class BookLoginServlet
 */
@WebServlet("/login")
public class BookLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String callback = request.getParameter("callback");
		
		BookService service = new BookService();
		boolean result = service.login(id,pass);
		System.out.println("in servlet : "+result);
		
		if(result){
			HttpSession session = request.getSession(true);
			session.setAttribute("id",id);
			session.setAttribute("login", "ok");
		}
		
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
