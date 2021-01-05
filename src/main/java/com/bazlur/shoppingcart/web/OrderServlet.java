package com.bazlur.shoppingcart.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bazlur.shoppingcart.dto.ShippingAddressDTO;
import com.bazlur.shoppingcart.repository.CartItemRepositoryImpl;
import com.bazlur.shoppingcart.repository.CartRepositoryImpl;
import com.bazlur.shoppingcart.repository.OrderRepositoryImpl;
import com.bazlur.shoppingcart.repository.ProductRepositoryImpl;
import com.bazlur.shoppingcart.repository.ShippingAddressRepositoryImpl;
import com.bazlur.shoppingcart.service.CartService;
import com.bazlur.shoppingcart.service.CartServiceImpl;
import com.bazlur.shoppingcart.service.OrderServiceImpl;
import com.bazlur.shoppingcart.service.OrderService;
import com.bazlur.shoppingcart.util.SecurityContext;
import com.bazlur.shoppingcart.util.ValidationUtil;

public class OrderServlet extends HttpServlet{
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServlet.class);
	
	private CartService cartService
	= new CartServiceImpl(new CartRepositoryImpl(),
			new ProductRepositoryImpl(),
					new CartItemRepositoryImpl());
	
	private OrderService orderService=new OrderServiceImpl(new OrderRepositoryImpl(),
															new ShippingAddressRepositoryImpl(),
															new CartRepositoryImpl());
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse rep) throws ServletException, IOException {
		addCartToUi(req);
		req.setAttribute("countries",getCountries());
		req.getRequestDispatcher("/WEB-INF/order.jsp").forward(req, rep);
				
	}
	private void addCartToUi(HttpServletRequest req) {
		// TODO Auto-generated method stub
		if(SecurityContext.isAuthenticated(req)) {
			var currentUser=SecurityContext.getCurrentUser(req);
			var cart=cartService.getCartByUser(currentUser);
			req.setAttribute("cart",cart);
		}
	}
	protected void doPost(HttpServletRequest req,HttpServletResponse rep) throws ServletException, IOException {
		LOGGER.info("Handle Order request form");
		var shippingAddress=copyParameterTo(req);
		LOGGER.info("shipping address information {}",shippingAddress);
		var errors=ValidationUtil.getInstance().validate(shippingAddress);
		if(!errors.isEmpty()) {
			req.setAttribute("countries",getCountries());
			req.setAttribute("errors",errors);
			req.setAttribute("shippingAddress",shippingAddress);
			addCartToUi(req);
			req.getRequestDispatcher("WEB-INF/order.jsp").forward(req, rep);
			}else {
				orderService.processOrder(shippingAddress,SecurityContext.getCurrentUser(req));
				rep.sendRedirect("/home?orderSuccess=true");
				
			}
	}
	
	private ShippingAddressDTO copyParameterTo(HttpServletRequest req) {
		var shippingAddress=new ShippingAddressDTO();
		shippingAddress.setAddress1(req.getParameter("address"));
		shippingAddress.setAddress2(req.getParameter("address2"));
		shippingAddress.setState(req.getParameter("state"));
		shippingAddress.setZip(req.getParameter("zip"));
		shippingAddress.setCountry(req.getParameter("country"));
		shippingAddress.setMobileNumber(req.getParameter("mobileNumber"));

		return shippingAddress;

	}
	private List<String> getCountries(){
		return List.of("Bangladesh","Switzerland","Japan","Canada","USA");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
