package com.hp.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.po.User;
import com.hp.service.UserService;

/**
 * servlet: 1.extends HttpServlet 2.定义servlet要处理的地址 a.注解式 b.配置 web.xml 3.找到请求的方法
 * 
 * @author HP
 *
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * 一般，业务层变量的定义，定义为类的成员变量 servlet中一般都doGet，doPost方法 如果定义为局部变量，用到后每个方法里都需要定义
	 */
	private UserService us = new UserService();

	/**
	 * 使用doGet还是doPost是由页面的数据发送方式决定的 method = "post"
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 * 1.接收页面请求参数 2.调用service层，业务处理，拿到结果 3.把数据封装到request 4.页面跳转
		 */
		/**
		 * 通过request.getParameter获取页面的请求参数 参数名称为页面控件的name
		 */
		String name = req.getParameter("username");
		String pwd = req.getParameter("password");
		// System.out.println(name);
		User u = new User();
		u.setName(name);
		u.setPwd(pwd);

		u = us.findUser(u);

		// 如果对象值是null，则跳转到登录页面
		if (null == u) {
			/**
			 * 页面跳转：有两种方式：
			 * req.getRequestDispatcher("login.jsp").forward(req, resp)
			 * resp.sendRedirect("login.jsp");
			 * 区别：1.url地址不同，前者通过servlet跳转，后者直接定位到某个页面
			 * 		2.如果页面想获取后台数据，只能用前者，后者丢失请求响应数据
			 */
			req.getRequestDispatcher("login.jsp").forward(req, resp);
//			resp.sendRedirect("login.jsp");
		} else {
			/**
			 * session中一般存放多个请求和相应之间的共用信息，例如用户个人信息
			 * 多个请求响应之间需要共享数据，不保存到session，信息获取困难，可以临时借用session
			 * 使用完毕后，在session中清除
			 * req.getSession().removeAttribute("user");
			 * 如果不是共享数据，尽量少使用session
			 */
			req.getSession().setAttribute("user", u);
			req.getRequestDispatcher("/userQueryServlet?action=search").forward(req, resp);
//			/**
//			 * request作用域在于一次请求和响应，当响应完毕，request作用域结束 request中保存的信息也消失
//			 * 如果要在多个request-response之间共享数据，则使用session 举例：一般需要共享的数据是用户个人信息
//			 * 京东，把羽毛球拍加入购物车，系统认为是我加的 点击购物车，查询我的个人购物车列表，系统也认为是我做的操作
//			 * request作用域举例： 京东：搜索unix羽毛球拍，响应羽毛球拍结果
//			 * 搜索运动装，和前面羽毛球拍没关系了，前面的请求信息“羽毛球拍”丢失
//			 */
//			req.getSession().setAttribute("user", u);
//			List userList = us.findAllUsers2();
//			/**
//			 * 把数据封装到request里，随着页面跳转，传递到页面 封装的方式是setAttribute 第一个参数是给这个数据起的名字
//			 */
//			req.setAttribute("userList", userList);
//			/**
//			 * 通过request.getRequestDispathcer方法页面跳转 参数是跳转的页面
//			 * forword千万不要落下，参数是request和response
//			 */
//			req.getRequestDispatcher("success.jsp").forward(req, resp);
////			resp.sendRedirect("success.jsp");
		}
	}

}
