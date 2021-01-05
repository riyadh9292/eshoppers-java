package com.bazlur.shoppingcart.web;

import java.awt.Desktop.Action;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bazlur.shoppingcart.domain.User.Cart;
import com.bazlur.shoppingcart.domain.User.User;
import com.bazlur.shoppingcart.repository.CartItemRepositoryImpl;
import com.bazlur.shoppingcart.repository.CartRepositoryImpl;
import com.bazlur.shoppingcart.repository.ProductRepositoryImpl;
import com.bazlur.shoppingcart.service.CartService;
import com.bazlur.shoppingcart.service.CartServiceImpl;
import com.bazlur.shoppingcart.util.SecurityContext;
import com.bazlur.shoppingcart.util.StringUtil;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/add-to-cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER=LoggerFactory.getLogger(CartServlet.class);
	private CartService cartService=new CartServiceImpl(new CartRepositoryImpl(),new ProductRepositoryImpl(),new CartItemRepositoryImpl());
	
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		var productId=request.getParameter("productId");
		LOGGER.info("Received request to add product with id: {} to cart",productId);
		var cart=getCart(request);
		var action=request.getParameter("action");
		
		if(StringUtil.isNotEmpty(action)) {
			processCart(productId,action,cart);
			response.sendRedirect("/checkout");
			return;
		}
		
		
		cartService.addProductToCart(productId,cart);
		response.sendRedirect("/dropshop/home");
	}



public enum Action{
	ADD,
	REMOVE;
}

	private void processCart(String productId, String action, Cart cart) {
		// TODO Auto-generated method stub
		switch(Action.valueOf(action.toUpperCase())) {
		case ADD:
			LOGGER.info("Received request to add product with id: {} to cart",productId);
			cartService.addProductToCart(productId, cart);
			break;
		
		case REMOVE:
			LOGGER.info("Received request to add product with id: {} to cart",productId);
			cartService.removeProductToCart(productId,cart);
			break;
			
		}
		
	}





	private Cart getCart(HttpServletRequest request) {
		// TODO Auto-generated method stub
		final User currentUser=SecurityContext.getCurrentUser(request);
		return cartService.getCartByUser(currentUser);
	}

}
