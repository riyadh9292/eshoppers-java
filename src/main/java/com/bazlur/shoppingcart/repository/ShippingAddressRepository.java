package com.bazlur.shoppingcart.repository;

import com.bazlur.shoppingcart.domain.User.ShippingAddress;

public interface ShippingAddressRepository {
	ShippingAddress save(ShippingAddress shippingAddress);
}
