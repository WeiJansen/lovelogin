package com.hp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.po.FormBean;
import com.hp.service.UserService;
import com.hp.util.Page;

/**
 * 用于用户查询交互
 * 
 * @author HP
 *
 */
@WebServlet("/userQueryServlet")
public class UserQueryServlet extends HttpServlet {
	private UserService us = new UserService();

	/**
	 * get方式和post方式区别：
	 * 1.一般数据修改用post,登录用post。查询一般使用get
	 * 2.get方式请求时，地址栏会附带请求信息，post不会
	 * 3.post用于发送大量数据的情况
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("action");
		if("search".equals(action)) {
			search(req,resp);
		}
		/*if("login".equals(action)) {

			String name = "";
			List userList = us.findUserByName(name);
			req.setAttribute("userList", userList);
			req.getRequestDispatcher("success2.jsp").forward(req, resp);
		}*/
		
	}

	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = req.getParameter("searchName");
		String address = req.getParameter("address");
		String sex = req.getParameter("sex");
		String date = req.getParameter("createDate");
		String pageNum = req.getParameter("pageNum");
		FormBean form = new FormBean();
		form.setUsername(name);
		form.setAddress(address);
		form.setSex(sex);
		form.setDate(date);
		if(pageNum == null) {
			pageNum = "1";
		}
		if(null == name) {
			name="";
		}
		if(null == address) {
			address="";
		}
		if(null == sex) {
			sex="";
		}
		if(null == date) {
			date = "";
		}
		
		Page page = new Page();
		page.setCount(3);
		page.setPageNum(Integer.parseInt(pageNum));
		page = us.search(name,sex,address,date,page);
		req.setAttribute("page", page);
		req.setAttribute("form", form);
		
		req.getRequestDispatcher("success2.jsp").forward(req, resp);
	}

	/**
	 * LoginServlet在用户验证通过后
	 * 可以通过req.getRequestDispatcher跳转
	 * 跳转可以是jsp页面，也可以是请求路径
	 * 登录使用的post方式，所以请求跳转会执行到请求servlet doPost方法
	 * 因为在doGet方法里已经实现了查询数据的功能，因此，doPost方法可以直接调用doGet方法
	 * req.getRequestDispatcher("/userQueryServlet").forward(req, resp);
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	
}
