package com.bazlur.shoppingcart.repository;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.bazlur.shoppingcart.domain.User.Order;
import com.bazlur.shoppingcart.domain.User.User;

public class OrderRepositoryImpl implements OrderRepository{
	private static final Set<Order> ORDERS=new CopyOnWriteArraySet<>();

	@Override
	public Order save(Order order) {
		// TODO Auto-generated method stub
		ORDERS.add(order);
		return order;
	}

	@Override
	public Set<Order> findOrderByUser(User currentUser) {
		// TODO Auto-generated method stub
		return null;
	}

}
