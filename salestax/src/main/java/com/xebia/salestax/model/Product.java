package com.xebia.salestax.model;
/**
 * <b>Product</b>
 * Model class which contains product related properties.
 * @author Jitin Kumar
 *
 */
public class Product {
	private Integer quantity;
	private String name;
	private Double price;
	private Double priceWithTax;
	private Boolean isImported;
	private Category category;

	public Product() {

	}
	
	public Product(Integer quantity, String name, Double price, Boolean isImported, Category category) {
		this.quantity = quantity;
		this.name = name;
		this.price = price;
		this.isImported = isImported;
		this.category = category;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPriceWithTax() {
		return priceWithTax;
	}

	public void setPriceWithTax(Double priceWithTax) {
		this.priceWithTax = priceWithTax;
	}

	public Boolean getIsImported() {
		return isImported;
	}

	public void setIsImported(Boolean isImported) {
		this.isImported = isImported;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
