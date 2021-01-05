package com.bazlur.shoppingcart.repository;

import java.util.Set;

import com.bazlur.shoppingcart.domain.User.Order;
import com.bazlur.shoppingcart.domain.User.User;

public interface OrderRepository {
	Order save(Order order);

	Set<Order> findOrderByUser(User currentUser);

}
