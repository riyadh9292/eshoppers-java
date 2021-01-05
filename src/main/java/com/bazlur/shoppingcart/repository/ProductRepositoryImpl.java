package com.bazlur.shoppingcart.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.bazlur.shoppingcart.domain.User.Product;
import com.bazlur.shoppingcart.dto.ProductDTO;

public class ProductRepositoryImpl implements ProductRepository{

	private static final List<Product> ALL_PRODUCTS=List.of(
			new Product(1L,"Apple iPad","Apple ipad 10.2 32GB",BigDecimal.valueOf(369.99)),
			new Product(2L,"HeadPhones","Jabra Elite Blutooth Headphones",BigDecimal.valueOf(249.99)),
			new Product(3L,"USB data","Micro USB data cable",BigDecimal.valueOf(11.99)),
			new Product(4L,"Amazon Echo Dot","Amazon Echo DOt(3rd Gen) with Alexa - Charcoal",BigDecimal.valueOf(34.99))
			);
			
			

	@Override
	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
		return ALL_PRODUCTS;
	}



	@Override
	public Optional<Product> findById(Long ProductId) {
		// TODO Auto-generated method stub
		return findAllProducts().stream().filter(product -> product.getId().equals(ProductId)).findFirst();
	}

}
