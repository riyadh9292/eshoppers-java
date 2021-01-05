package com.bazlur.shoppingcart.service;

import com.bazlur.shoppingcart.domain.User.Order;
import com.bazlur.shoppingcart.domain.User.ShippingAddress;
import com.bazlur.shoppingcart.domain.User.User;
import com.bazlur.shoppingcart.dto.ShippingAddressDTO;
import com.bazlur.shoppingcart.exception.CartItemNotFoundException;
import com.bazlur.shoppingcart.repository.CartRepository;
import com.bazlur.shoppingcart.repository.OrderRepository;
import com.bazlur.shoppingcart.repository.ShippingAddressRepository;

public class OrderServiceImpl implements OrderService {
	private OrderRepository orderRepository;
	private ShippingAddressRepository shippingAddressRepository;
	private CartRepository cartRepository;
	
	

	public OrderServiceImpl(OrderRepository orderRepository, ShippingAddressRepository shippingAddressRepository,
			CartRepository cartRepository) {
		this.orderRepository = orderRepository;
		this.shippingAddressRepository = shippingAddressRepository;
		this.cartRepository = cartRepository;
	}



	@Override
	public void processOrder(ShippingAddressDTO shippingAddressDTO, User currentUser) {
		// TODO Auto-generated method stub
		var shippingAddress=convertTo(shippingAddressDTO);
		var savedShippingAddress=shippingAddressRepository.save(shippingAddress);
		var cart=cartRepository.findByUser(currentUser);
		
		Order order=new Order();
		order.setCart(cart);
		order.setShippingAddress(savedShippingAddress);
		order.setShipped(false);
		order.setUser(currentUser);
		orderRepository.save(order);

	}
	
	private ShippingAddress convertTo(ShippingAddressDTO shippingAddressDTO) {
		var shippingAddress=new ShippingAddress();
		shippingAddress.setAddress1(shippingAddress.getAddress1());
		shippingAddress.setAddress2(shippingAddress.getAddress2());
		shippingAddress.setCountry(shippingAddress.getCountry());
		shippingAddress.setState(shippingAddress.getState());
		shippingAddress.setZip(shippingAddress.getZip());
		shippingAddress.setMobileNumber(shippingAddress.getMobileNumber());
		return shippingAddress;





		
	}

}
