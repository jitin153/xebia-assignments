package com.xebia.salestax;

import com.xebia.salestax.model.Category;
import com.xebia.salestax.model.Product;
import com.xebia.salestax.util.Constants;
/**
 * <b>TaxCalculatorImpl</b>
 * Implementing TaxCalculator interface. Contains only 1 static method which is responsible to calculate tax.
 * @author Jitin Kumar
 *
 */
public class TaxCalculatorImpl implements TaxCalculator{

	/**
	 * Method to calculate tax for the provided product.
	 * @param product - Accepts Product whose tax to be calculated.
	 * @return Returns calculated tax.
	 */
	
	@Override
	public Double calculateTax(Product product) {
		if (null != product) {
			if (product.getCategory() == Category.TAX_EXEMPTED && product.getIsImported()) {
				return (product.getPrice() * Constants.IMPORT_DUTY_PERCENT) / 100.0;
			} else if (product.getCategory() == Category.NON_TAX_EXEMPTED && !product.getIsImported()) {
				return (product.getPrice() * Constants.SALES_TAX_PERCENT) / 100.0;
			} else if (product.getCategory() == Category.NON_TAX_EXEMPTED && product.getIsImported()) {
				return (product.getPrice()
						* (Constants.IMPORT_DUTY_PERCENT + Constants.SALES_TAX_PERCENT)) / 100.0;
			}
		}
		return 0.0;
	}
}
