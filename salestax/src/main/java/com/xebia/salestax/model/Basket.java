package com.xebia.salestax.model;

import java.util.Set;

import com.xebia.salestax.exception.SalesTaxException;

/**
 * <b>Basket</b>
 * 
 * @author Jitin Kumar
 *
 */
public class Basket {
	private Set<Product> products;
	private Double salesTax;
	private Double totalPrice;

	public Basket() {

	}

	public Basket(Set<Product> products) {
		this.products = products;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Double getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(Double salesTax) {
		this.salesTax = salesTax;
	}
	
	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	/**
	 * Method to print the invoice. Accepts nothing, returns nothing.
	 */
	public void printReceipt() {
		if (null != products && !products.isEmpty()) {
			products.forEach(product -> {
				System.out.println(product.getQuantity() + " " + product.getName() + " : " + product.getPriceWithTax());
			});
			System.out.println("Sales Taxes: " + this.salesTax.doubleValue() + "\nTotal: " + this.getTotalPrice());
		} else {
			throw new SalesTaxException("Product list is empty.");
		}
	}
}
