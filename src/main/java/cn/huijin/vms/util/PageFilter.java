package cn.huijin.vms.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sylarlove.advance.model.PageContext;

public class PageFilter implements Filter {
	static final Logger logger=LoggerFactory.getLogger(PageFilter.class);

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request.getParameter("page")!=null) PageContext.setCurrentPage(Integer.parseInt(request.getParameter("page")));
		if(request.getParameter("limit")!=null) PageContext.setCurrentPage(Integer.parseInt( request.getParameter("limit")));
		logger.info("page:{} limit:{}",request.getParameter("page"),request.getParameter("limit"));
		chain.doFilter(request, response);
		PageContext.removeAll();
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


}
