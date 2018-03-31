package com.hp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hp.po.User;

@WebFilter(filterName = "securityFilter", urlPatterns="/*")
public class SecurityFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse respsonse, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) respsonse;
		Object user = req.getSession().getAttribute("user");

		String uri = req.getRequestURI();
//		System.out.println(uri);
		if (uri.endsWith("login.jsp") || uri.endsWith("loginServlet") || uri.endsWith("account.jsp") || uri.endsWith("gif") || uri.endsWith("css")){
			chain.doFilter(req, resp);
		}else if (null == user){
			resp.sendRedirect("login.jsp");
		}else{
			chain.doFilter(req, resp);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
