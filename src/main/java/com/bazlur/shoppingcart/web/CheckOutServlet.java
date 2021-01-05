package com.bazlur.shoppingcart.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bazlur.shoppingcart.repository.CartItemRepositoryImpl;
import com.bazlur.shoppingcart.repository.CartRepositoryImpl;
import com.bazlur.shoppingcart.repository.ProductRepositoryImpl;
import com.bazlur.shoppingcart.service.CartService;
import com.bazlur.shoppingcart.service.CartServiceImpl;
import com.bazlur.shoppingcart.util.SecurityContext;

@WebServlet("/checkout")
public class CheckOutServlet  extends HttpServlet{
	private static final Logger LOGGER=LoggerFactory.getLogger(CheckOutServlet.class);
	
	private CartService cartService=new CartServiceImpl(new CartRepositoryImpl(),new ProductRepositoryImpl(),new CartItemRepositoryImpl());
	
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("serving checkout page");
		var currentUser=SecurityContext.getCurrentUser(req);
		var cart= cartService.getCartByUser(currentUser);
		req.setAttribute("cart", cart);
		req.getRequestDispatcher("WEB-INF/checkout.jsp").forward(req, resp);
		
		
		
	}



}