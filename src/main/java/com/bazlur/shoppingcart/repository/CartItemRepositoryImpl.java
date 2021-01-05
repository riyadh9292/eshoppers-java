package com.bazlur.shoppingcart.repository;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.bazlur.shoppingcart.domain.User.CartItem;

public class CartItemRepositoryImpl implements CartItemRepository{
	private static final Set<CartItem>CARTS=new CopyOnWriteArraySet<>();

	@Override
	public CartItem save(CartItem cartItem) {
		// TODO Auto-generated method stub
		CARTS.add(cartItem);
		return cartItem;
		
		
	}

	@Override
	public CartItem update(CartItem cartItem) {
		// TODO Auto-generated method stub
		CARTS.add(cartItem);
		return cartItem;
	}

	@Override
	public void remove(CartItem cartItem) {
		// TODO Auto-generated method stub
		CARTS.remove(cartItem);
		
	}

}
