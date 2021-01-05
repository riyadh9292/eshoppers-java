package com.bazlur.shoppingcart.web;

//import java.util.logging.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bazlur.shoppingcart.domain.User.User;
import com.bazlur.shoppingcart.dto.LoginDTO;
import com.bazlur.shoppingcart.exceptions.UserNotFoundException;
import com.bazlur.shoppingcart.repository.UserRepository;
import com.bazlur.shoppingcart.repository.UserRepositoryImpl;
import com.bazlur.shoppingcart.service.UserService;
import com.bazlur.shoppingcart.service.UserServiceImpl;
import com.bazlur.shoppingcart.util.SecurityContext;
import com.bazlur.shoppingcart.util.ValidationUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	private static final Logger LOGGER= LoggerFactory.getLogger(LoginServlet.class);
	private UserService userService=new UserServiceImpl(new UserRepositoryImpl());
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse rep) throws ServletException, IOException {
		LOGGER.info("serving login page");
		String logout=req.getParameter("logout");
		if(logout !=null && Boolean.parseBoolean(logout)) {
			req.setAttribute("message","You have successfully logged out.");
		}
		req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, rep);
		
	}
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse rep) throws ServletException, IOException {
		var loginDTO=new LoginDTO(req.getParameter("username"),req.getParameter("password"));
		LOGGER.info("Received long data: {}",loginDTO);
		var errors=ValidationUtil.getInstance().validate(loginDTO);
		if(!errors.isEmpty()) {
			LOGGER.info("failed to login");
			req.setAttribute("errors",errors);
			req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, rep);
			
		}
		try {
			login(loginDTO,req);
			LOGGER.info("Login successful,redirect to homepage");
			rep.sendRedirect("/dropshop/home");
		}catch(UserNotFoundException e) {
			LOGGER.error("incorrect username/password", e);
			errors.put("username", "Incorrect username");
			errors.put("password","incorrect password");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, rep);			
		}
		
		
		
		}
	private void login(LoginDTO loginDTO,HttpServletRequest req) throws UserNotFoundException{
		User user=userService.verifyUser(loginDTO);
		SecurityContext.login(req, user);
		
	}

}
