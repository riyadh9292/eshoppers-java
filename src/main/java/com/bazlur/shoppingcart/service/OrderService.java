package com.bazlur.shoppingcart.service;

import com.bazlur.shoppingcart.domain.User.User;
import com.bazlur.shoppingcart.dto.ShippingAddressDTO;

public interface OrderService {
	void processOrder(ShippingAddressDTO shippingAddress,User currentUser);

}
