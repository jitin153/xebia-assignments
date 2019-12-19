package com.xebia.salestax.util;

import com.xebia.salestax.exception.SalesTaxException;

/**
 * <b>MathUtil</b>
 * Utility class contains 2 method to round number upto 2 decimal places.
 * 
 * @author Jitin Kumar
 *
 */
public class MathUtil {
	/**
	 * Method to round number up to the nearest 0.05
	 * 
	 * @param number
	 *            - Accepts number be rounded as Double.
	 * @return Returns rounded number.
	 */
	public static Double roundTax(Double number) {
		if (null != number) {
			return Math.ceil(number * 20) / 20;
		}
		throw new SalesTaxException("Number cannot be null.");
	}

	/**
	 * Method to round number up to 2 decimal places.
	 * 
	 * @param number
	 *            - Accepts number be rounded as Double.
	 * @return Returns rounded number.
	 */
	public static Double roundPrice(Double number) {
		if (null != number) {
			return Math.round(number * 100.0) / 100.0;
		}
		throw new SalesTaxException("Number cannot be null.");
	}
}
