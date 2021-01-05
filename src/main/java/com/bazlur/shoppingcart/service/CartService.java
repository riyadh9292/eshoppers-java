package com.bazlur.shoppingcart.service;

import com.bazlur.shoppingcart.domain.User.Cart;
import com.bazlur.shoppingcart.domain.User.Product;
import com.bazlur.shoppingcart.domain.User.User;

public interface CartService {
	Cart getCartByUser(User currentUser) ;
	void addProductToCart(String productId, Cart cart);
	void removeProductToCart(String productId,Cart cart);
	

}
