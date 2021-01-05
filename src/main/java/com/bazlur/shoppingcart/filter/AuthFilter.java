package com.bazlur.shoppingcart.filter;

import java.io.IOException;
import java.util.stream.Stream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bazlur.shoppingcart.util.SecurityContext;

@WebServlet(urlPatterns="/dropshop/*")
public class AuthFilter implements Filter{
	private static final String[] ALLOWED_CONTENT= {".css",".js",".jsp","dropshop/home","login","signup"};

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		var httpServletRequest=(HttpServletRequest) request;
		var requestedUri=httpServletRequest.getRequestURI();
		boolean allowed=Stream.of(ALLOWED_CONTENT)
				.anyMatch(requestedUri::contains);
		if(requestedUri.equals("/") || allowed || SecurityContext.isAuthenticated(httpServletRequest)) {
			chain.doFilter(request, response);
			
		} else {
			((HttpServletResponse) response).sendRedirect("/dropshop/login");
		}
		
	}

}
