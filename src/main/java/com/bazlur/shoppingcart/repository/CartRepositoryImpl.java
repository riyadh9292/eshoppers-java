package com.bazlur.shoppingcart.repository;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.bazlur.shoppingcart.domain.User.Cart;
import com.bazlur.shoppingcart.domain.User.Order;
import com.bazlur.shoppingcart.domain.User.User;

public class CartRepositoryImpl implements CartRepository{
	private static final Map<User,Set<Cart>> CARTS=new ConcurrentHashMap<>();
	private OrderRepository orderRepository=new OrderRepositoryImpl();

	@Override
	public Optional<Cart> findByUser(User currentUser) {
		// TODO Auto-generated method stub
		var usersCart=getCart(currentUser);
		if(usersCart.isPresent()) {
			var cart=usersCart.get();
			var orders=orderRepository.findOrderByUser(currentUser);
			if(isOrderAlreadyPlacedWith(orders,cart)) {
				return Optional.empty();
			}else {
			return Optional.of(cart);
			}
		}
		return Optional.empty();
	}

	private boolean isOrderAlreadyPlacedWith(Set<Order> orderByUser, Cart cart) {
		// TODO Auto-generated method stub
		
		return orderByUser.stream().noneMatch(order -> order.getCart().equals(cart));
	}

	private Optional<Cart> getCart(User currentUser) {
		// TODO Auto-generated method stub
		var carts=CARTS.get(currentUser);
		if(carts != null && !carts.isEmpty()) {
			Cart cart=(Cart) carts.toArray()[carts.size()-1];
			return Optional.of(cart);
		}
		return Optional.empty();
		
	}

	@Override
	public Cart save(Cart cart) {
		// TODO Auto-generated method stub
		CARTS.computeIfPresent(cart.getUser(), (user,carts) ->{
			carts.add(cart);
			return carts;
		});
		CARTS.computeIfAbsent(cart.getUser(),
								user -> {
									var carts=new LinkedHashSet<Cart>();
									carts.add(cart);
									return carts;
								});
		return cart;
	}

	@Override
	public Cart update(Cart cart) {
		// TODO Auto-generated method stub
		CARTS.computeIfPresent(cart.getUser(), 
				(user, carts) -> {
					Cart[] objects= carts.toArray(Cart[]::new);
					objects[objects.length-1]=cart;
					return new LinkedHashSet<>(Arrays.asList(objects));
				});
		return cart;
	}

}
