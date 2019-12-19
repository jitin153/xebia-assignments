package com.xebia.salestax;

import com.xebia.salestax.model.Product;

/**
 * <b>TaxCalculator</b>
 * @author Jitin Kumar
 *
 */
public interface TaxCalculator {
	/**
	 * Method to calculate tax.
	 * @param product
	 * @return Returns calculated tax.
	 */
	public Double calculateTax(Product product);
}
