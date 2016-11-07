package com.cjon.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class BookSessionServlet
 */
@WebServlet("/sessionchk")
public class BookSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BookSessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String callback = request.getParameter("callback");
		
		HttpSession session = request.getSession(true);
		
		 JSONArray arr = new JSONArray();
		JSONObject obj = new JSONObject();
		obj.put("login", session.getAttribute("login"));
		obj.put("id",	session.getAttribute("id"));
		arr.add(obj);
			
		String	result = arr.toJSONString();
		
		
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
