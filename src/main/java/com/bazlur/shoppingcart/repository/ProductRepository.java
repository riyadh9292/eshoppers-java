package com.bazlur.shoppingcart.repository;

import java.util.List;
import java.util.Optional;

import com.bazlur.shoppingcart.domain.User.Cart;
import com.bazlur.shoppingcart.domain.User.Product;

public interface ProductRepository {
	List<Product> findAllProducts();

	Optional<Product> findById(Long ProductId);

}
