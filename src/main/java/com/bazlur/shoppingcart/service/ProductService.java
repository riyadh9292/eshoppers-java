package com.bazlur.shoppingcart.service;

import java.util.List;

import com.bazlur.shoppingcart.dto.ProductDTO;

public interface ProductService {
	List<ProductDTO> findAllProductSortedByName();

}
