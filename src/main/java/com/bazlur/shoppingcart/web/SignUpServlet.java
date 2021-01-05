package com.bazlur.shoppingcart.web;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bazlur.shoppingcart.dto.UserDTO;
import com.bazlur.shoppingcart.repository.UserRepositoryImpl;
import com.bazlur.shoppingcart.service.UserService;
import com.bazlur.shoppingcart.service.UserServiceImpl;
import com.bazlur.shoppingcart.util.ValidationUtil;

@WebServlet("/signup")

public class SignUpServlet extends HttpServlet{
	private final static Logger LOGGER=LoggerFactory.getLogger(SignUpServlet.class);
	private UserService userService=new UserServiceImpl(new UserRepositoryImpl());	
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse rep) throws ServletException, IOException {
		LOGGER.info("serving sign up page");
		req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, rep);
		
	}
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse rep) throws ServletException, IOException {
		var userDTO=copyParametersTo(req);
		var errors=ValidationUtil.getInstance().validate(userDTO);
		//this need to check further
		if(!errors.isEmpty()) {
			req.setAttribute("userDTO", userDTO);
			req.setAttribute("errors", errors);
			LOGGER.info("user sent invalid data: {}",userDTO);
			req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, rep);
		}else if(userService.isNotUniqueUsername(userDTO)){
			LOGGER.info("USERNAME: {} already exist",userDTO.getUsername());
			errors.put("username", "The username already exists.Please use a different username");
			req.setAttribute("userDTO",userDTO);
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, rep);
		}
		else if(userService.isNotUniqueEmail(userDTO)){
			LOGGER.info("EMAIL: {} already exist",userDTO.getEmail());
			errors.put("email", "The email already exists.Please use a different one");
			req.setAttribute("userDTO",userDTO);
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("/WEB-INF/signup.jsp").forward(req, rep);
		}
		else {
			LOGGER.info("user is valid, creating a new user with:{}",userDTO);
			userService.saveUser(userDTO);
			rep.sendRedirect("/dropshop/login");
		}
	}
	
	private UserDTO copyParametersTo(HttpServletRequest req) {
		// TODO Auto-generated method stub
		var userDTO=new UserDTO();
		userDTO.setFirstName(req.getParameter("firstName"));
		userDTO.setLastName(req.getParameter("lastName"));
		userDTO.setPassword(req.getParameter("password"));
		userDTO.setPasswordConfirmed(req.getParameter("passwordConfirmed"));
		userDTO.setEmail(req.getParameter("email"));
		userDTO.setUsername(req.getParameter("username"));
		
		return userDTO;
	}
	/*private Map<String,String> validate(UserDTO userDTO) {
		// TODO Auto-generated method stub
		var validatorFactory=Validation.buildDefaultValidatorFactory();
		var validator=validatorFactory.getValidator();
		Set<ConstraintViolation<UserDTO>> violations=validator.validate(userDTO);
		Map<String,String> errors=new HashMap<>();
		for(ConstraintViolation<UserDTO> violation : violations) {
			String path=violation.getPropertyPath().toString();
			if(errors.containsKey(path)) {
				String errormsg=errors.get(path);
				errors.put(path, errormsg+"<br/>"+violation.getMessage());
			}else {
				errors.put(path, violation.getMessage());
			}
		}
		return errors;
	}*/
	

}
