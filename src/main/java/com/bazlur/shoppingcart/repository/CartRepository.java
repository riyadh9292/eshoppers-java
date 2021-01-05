package com.bazlur.shoppingcart.repository;

import java.util.Optional;

import com.bazlur.shoppingcart.domain.User.Cart;
import com.bazlur.shoppingcart.domain.User.User;

public interface CartRepository {

	Optional<Cart> findByUser(User currentUser);

	Cart save(Cart cart);
	Cart update(Cart cart);

}
