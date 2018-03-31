package com.hp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.po.User;
import com.hp.service.UserService;

@WebServlet("/userUpdateServlet")
public class UserUpdateServlet extends HttpServlet {

	private UserService us = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**
		 * request可以接受页面的请求参数 1.表单，引用页面控件的name
		 * 2.可以通过超链接获取请求参数，超链接传递参数的方式是?key=value
		 * 
		 * request的作用： 1.获得请求参数，getParameter,getContextPath
		 * 2.传递页面信息的值，request.setAttribute 3.获取当前的session，request.getSession
		 * 4.页面跳转，request.getRequestDispather.forword
		 */
		String id = req.getParameter("id");

		int id1 = 0;
		try {
			id1 = Integer.valueOf(id);
		} catch (Exception e) {
			id1 = 0;
		}
		User u = us.findUserById(id1);
		if (u != null) {
			req.setAttribute("user", u);
			req.getRequestDispatcher("update.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		String role = req.getParameter("role");
		
		User u = new User();
		u.setId(Integer.valueOf(id));
		u.setPwd(pwd);
		u.setRole(Integer.valueOf(role));
		
		us.updateUser(u);
		
		req.getRequestDispatcher("/userQueryServlet").forward(req, resp);
	}

}
