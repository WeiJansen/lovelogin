package com.hp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * 过滤器可以拦截请求，对请求的数据做处理
 * @author HP
 *
 */
@WebFilter(filterName="charactorEncodingFilter",urlPatterns="/*")
public class CharactorEncodingFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		/**
		 * 通过chain.doFilter继续执行
		 * 如果本过滤器执行完后没有其他过滤器需要执行，则执行请求
		 * 如果有多个过滤器，则执行其他过滤器
		 */
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
