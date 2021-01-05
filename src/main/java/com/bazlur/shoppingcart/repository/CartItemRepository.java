package com.bazlur.shoppingcart.repository;

import com.bazlur.shoppingcart.domain.User.CartItem;

public interface CartItemRepository {

	CartItem save(CartItem cartItem);

	CartItem update(CartItem cartItem);
	void remove(CartItem cartItem);

}
