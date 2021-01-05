package com.bazlur.shoppingcart.repository;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.bazlur.shoppingcart.domain.User.ShippingAddress;

public class ShippingAddressRepositoryImpl implements ShippingAddressRepository {
	private final Set<ShippingAddress> SHIPPING_ADDRESS=new CopyOnWriteArraySet<>();

	@Override
	public ShippingAddress save(ShippingAddress shippingAddress) {
		// TODO Auto-generated method stub
		SHIPPING_ADDRESS.add(shippingAddress);
		return shippingAddress;
	}

}
