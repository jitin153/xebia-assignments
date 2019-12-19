package com.xebia.salestax;

import com.xebia.salestax.model.Basket;

/**
 * <b>TaxApplier</b>
 * 
 * @author Jitin Kumar
 *
 */
public interface TaxApplier {
	/**
	 * @param basket
	 * @return Return Basket object after calculating & applying taxes.
	 */
	public Basket applyTaxesAndCalculatePrice(Basket basket);
}
