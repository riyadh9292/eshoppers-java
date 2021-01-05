package com.bazlur.shoppingcart.web;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;

import com.bazlur.shoppingcart.domain.User.CartItem;
import com.bazlur.shoppingcart.dto.ProductDTO;
import com.bazlur.shoppingcart.repository.CartItemRepositoryImpl;
import com.bazlur.shoppingcart.repository.CartRepositoryImpl;
import com.bazlur.shoppingcart.repository.ProductRepositoryImpl;
import com.bazlur.shoppingcart.service.CartService;
import com.bazlur.shoppingcart.service.CartServiceImpl;
import com.bazlur.shoppingcart.service.ProductService;
import com.bazlur.shoppingcart.service.ProductServiceImpl;
import com.bazlur.shoppingcart.util.SecurityContext;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeServlet.class);

    private ProductService productService=new ProductServiceImpl(new ProductRepositoryImpl());
	private CartService cartService=new CartServiceImpl(new CartRepositoryImpl(),new ProductRepositoryImpl(),new CartItemRepositoryImpl());

	@Override

public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		LOGGER.info("serving home page");
		final String attribute=req.getParameter("orderSuccess");
		if(attribute !=null && Boolean.parseBoolean(attribute)) {
			req.setAttribute("message","<strong>Congratulation!</strong>"
					+ "Order has been placed successfully.");
		}
		List<ProductDTO> allProducts=productService.findAllProductSortedByName();
		LOGGER.info("Total product found {}",allProducts.size());
		
		//Cart cart=cartService.getCartByUser(SecurityContext.getCurrentUser(req));
		if(SecurityContext.isAuthenticated(req)) {
			var currentUser= SecurityContext.getCurrentUser(req);
			var cart = cartService.getCartByUser(currentUser);
			req.setAttribute("cart",cart);
		}
		req.setAttribute("products",allProducts);
		req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    
        
    }
}

