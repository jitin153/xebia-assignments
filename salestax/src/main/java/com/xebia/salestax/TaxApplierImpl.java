package com.xebia.salestax;

import com.xebia.salestax.exception.SalesTaxException;
import com.xebia.salestax.model.Basket;
import com.xebia.salestax.model.Product;
import com.xebia.salestax.util.MathUtil;
/**
 * <b>TaxApplierImpl</b>
 * Implementing TaxApplier interface.
 * @author Jitin Kumar
 *
 */
public class TaxApplierImpl implements TaxApplier {

	private TaxCalculator taxCalculator;

	public TaxApplierImpl(TaxCalculator taxCalculator) {
		this.taxCalculator = taxCalculator;
	}

	/**
	 * @param basket
	 * @return Return Basket object after calculating & applying taxes.
	 */
	@Override
	public Basket applyTaxesAndCalculatePrice(Basket basket) {
		if (null != basket && null != basket.getProducts() && !basket.getProducts().isEmpty()) {
			Double totalSalesTax = 0.0;
			Double totalPrice = 0.0;
			for (Product product : basket.getProducts()) {
				Double tax = taxCalculator.calculateTax(product);
				totalSalesTax += tax;
				Double priceWithTax = (tax > 0)
						? ((product.getPrice() + MathUtil.roundTax(tax)) * product.getQuantity())
						: (product.getPrice() * product.getQuantity());
				product.setPriceWithTax(MathUtil.roundPrice(priceWithTax));
				totalPrice += priceWithTax;
			}
			basket.setSalesTax(MathUtil.roundTax(totalSalesTax));
			basket.setTotalPrice(MathUtil.roundPrice(totalPrice));
			return basket;
		}
		throw new SalesTaxException("You cannot perform calculation on empty product list.");
	}

}
