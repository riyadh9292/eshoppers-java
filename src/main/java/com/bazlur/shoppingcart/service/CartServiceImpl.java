package com.bazlur.shoppingcart.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.slf4j.LoggerFactory;

import com.bazlur.shoppingcart.domain.User.Cart;
import com.bazlur.shoppingcart.domain.User.CartItem;
import com.bazlur.shoppingcart.domain.User.Product;
import com.bazlur.shoppingcart.domain.User.User;
import com.bazlur.shoppingcart.exception.CartItemNotFoundException;
import com.bazlur.shoppingcart.exceptions.ProductNotFoundException;
import com.bazlur.shoppingcart.repository.CartItemRepository;
import com.bazlur.shoppingcart.repository.CartRepository;
import com.bazlur.shoppingcart.repository.ProductRepository;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
public class CartServiceImpl implements CartService{
	private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);
	private final CartRepository cartRepository;
	private final ProductRepository productRepository;
	private final CartItemRepository cartItemRepository;
	
	

	public CartServiceImpl(CartRepository cartRepository,ProductRepository productRepository,CartItemRepository cartItemRepository) {
		this.cartRepository = cartRepository;
		this.productRepository=productRepository;
		this.cartItemRepository=cartItemRepository;
	}


	@Override
	public Cart getCartByUser(User currentUser) {
		// TODO Auto-generated method stub
		Optional<Cart> optionalCart=cartRepository.findByUser(currentUser);
		return optionalCart.orElseGet(() -> createNewCart(currentUser));
	}


	@Override
	public void addProductToCart(String productId, Cart cart) {
		// TODO Auto-generated method stub
		Product product =findProduct(productId);
		addProductToCart(product,cart);
		updateCart(cart);
		
		
	}
	private void addProductToCart(Product product,Cart cart) {
		var cartItemOptional=findSimilarProductInCart(cart,product);
		var cartItem = cartItemOptional
						.map(this::increaseQuantityByOne)
						.orElseGet(() -> createNewCartItem(product));
		cart.getCartItem().add(cartItem);
	}
	private CartItem createNewCartItem(Product product) {
		var cartItem=new CartItem();
		cartItem.setProduct(product);
		cartItem.setQuantity(1);
		cartItem.setPrice(product.getPrice());
		
		return cartItemRepository.save(cartItem);
	}
	private CartItem increaseQuantityByOne(CartItem cartItem) {
		cartItem.setQuantity(cartItem.getQuantity()+1);
		BigDecimal totalPrice=cartItem.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));
		cartItem.setPrice(totalPrice);
		return cartItemRepository.update(cartItem);
	}
	
	private Optional<CartItem> findSimilarProductInCart(Cart cart,Product product){
		return cart.getCartItem().stream()
								.filter(cartItem->
								
										cartItem.getProduct().equals(product))
												.findFirst();
	}
	

	private BigDecimal calculateTotalPrice(Cart cart) {
		// TODO Auto-generated method stub
		return cart.getCartItem()
					.stream().map(CartItem::getPrice)
					.reduce(BigDecimal.ZERO,BigDecimal::add);
	}


	private Integer getTotalItem(Cart cart) {
		// TODO Auto-generated method stub
		return cart.getCartItem()
				.stream().map(CartItem::getQuantity)
				.reduce(0, Integer::sum);
	}


	private Long parseProductId(String productId) {
		// TODO Auto-generated method stub
		try {
			return Long.parseLong(productId);
		}catch(NumberFormatException ex) {
			throw new IllegalArgumentException(
					"Product id must be a number",ex);
		}
	}
	private Cart createNewCart(User currentUser) {
		// TODO Auto-generated method stub
		Cart cart=new Cart();
		cart.setUser(currentUser);
		return cartRepository.save(cart);
	}


	
	public void removeProductToCart(Product productToRemove, Cart cart) {
		// TODO Auto-generated method stub
		var itemOptional = cart.getCartItem()
								.stream()
								.filter(cartItem -> 
										cartItem.getProduct().equals(productToRemove))
								.findAny();
		var cartItem = itemOptional.orElseThrow();
		if (cartItem.getQuantity() > 1) {
			cartItem.setQuantity(cartItem.getQuantity() - 1);
			cartItem.setPrice(cartItem.getPrice().subtract(productToRemove.getPrice()));
			cart.getCartItem().add(cartItem);
			cartItemRepository.update(cartItem);
		} else {
			cart.getCartItem().remove(cartItem);
			cartItemRepository.remove(cartItem);
		}
		
	}


	private void updateCart(Cart cart) {
		// TODO Auto-generated method stub
		Integer totalItem= getTotalItem(cart);
		BigDecimal totalPrice = calculateTotalPrice(cart);
		cart.setTotalItem(totalItem);
		cart.setTotalPrice(totalPrice);
		
		cartRepository.update(cart);
		
	}


	private Product findProduct(String productId) {
		// TODO Auto-generated method stub
		if(productId==null|| productId.length()==0) {
			throw new IllegalArgumentException("Product id can not be null");
			
		}
		Long id = parseProductId(productId);
		return productRepository.findById(id)
				.orElseThrow(()-> new ProductNotFoundException(
						"Product not found by id: "+id));

	}


	@Override
	public void removeProductToCart(String productId, Cart cart) {
		// TODO Auto-generated method stub
		Product product = findProduct(productId);
		removeProductToCart(product,cart);
		updateCart(cart);
		
		
	}
	
	



}
