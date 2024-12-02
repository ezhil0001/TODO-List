package com.Servlet;

import java.io.IOException;

import com.dao.TodoDAO;
import com.db.DBConnect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/add_todo")
public class AddServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String username=req.getParameter("username");
	String todo=req.getParameter("todo");
	String status=req.getParameter("status");

//	System.out.println(username+" "+todo+" "+status);
	
	TodoDAO dao=new TodoDAO(DBConnect.getConn());
	boolean f=dao.addTodo(username, todo, status);
	
	
	HttpSession session=req.getSession();
	
	
	if(f) {
		session.setAttribute("sucMsg","Todo Added Successfully...");
		System.out.println("Data Insert Successfully...!");
		resp.sendRedirect("index.jsp");
	}else {
		
		session.setAttribute("failedMsg","Something Wrong on Server...!");
		System.out.println("Error..");
		resp.sendRedirect("index.jsp");
		
	}
	
		
	}
	
	

}
