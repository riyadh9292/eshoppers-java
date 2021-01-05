package com.bazlur.shoppingcart.dto;

import java.math.BigDecimal;

public class ProductDTO {
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	public String getName() {
		return name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public ProductDTO(Long id, String name, String description, BigDecimal price) {
		this.id=id;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	

}
