package com.bazlur.shoppingcart.web;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bazlur.shoppingcart.util.SecurityContext;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final Logger LOGGER=LoggerFactory.getLogger(LogoutServlet.class);
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse rep) throws IOException {
		LOGGER.info("user logging out");
		SecurityContext.logout(req);
		rep.sendRedirect("/eshoppers/login?logout=true");
		
	}

}
