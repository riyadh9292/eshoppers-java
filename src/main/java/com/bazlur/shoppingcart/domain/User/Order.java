package com.bazlur.shoppingcart.domain.User;

import java.time.LocalDateTime;
import java.util.Optional;

public class Order {
	private Long id;
	private Optional<Cart> cart;
	private ShippingAddress shippingAddress;
	private LocalDateTime shippingDate;
	private Payment payment;
	private boolean shipped;
	private User user;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Optional<Cart> getCart() {
		return cart;
	}
	public void setCart(Optional<Cart> cart2) {
		this.cart = cart2;
	}
	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public LocalDateTime getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(LocalDateTime shippingDate) {
		this.shippingDate = shippingDate;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public boolean isShipped() {
		return shipped;
	}
	public void setShipped(boolean shipped) {
		this.shipped = shipped;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	

}
