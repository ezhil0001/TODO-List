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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int id = Integer.parseInt(req.getParameter("id"));
		
		TodoDAO dao=new TodoDAO(DBConnect.getConn());
		
		boolean f=dao.deleteTodo(id);
		
		HttpSession session=req.getSession();
		
		if(f) {
			session.setAttribute("sucMsg","Todo Delete Successfully...");
			System.out.println("Data Insert Successfully...!");
			resp.sendRedirect("index.jsp");
		}else {
			
			session.setAttribute("failedMsg","Something Wrong on Server...!");
			System.out.println("Error..");
			resp.sendRedirect("index.jsp");
			
		}
	}

	

}
