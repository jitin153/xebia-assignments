package com.xebia.salestax;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import com.xebia.salestax.exception.SalesTaxException;
import com.xebia.salestax.model.Basket;
import com.xebia.salestax.model.Category;
import com.xebia.salestax.model.Product;
import com.xebia.salestax.util.Constants;
/**
 * <b>SalesTaxAppStarter</b>
 * Driver class to run the application.
 * @author Jitin Kumar
 *
 */
public class SalesTaxAppStarter {

	public static void main(String[] args) {
		List<Product> products;
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("For how many baskets you want to calculate tax? ");
			int numberOfBasket = Integer.parseInt(scanner.nextLine());
			for (int i = 1; i <= numberOfBasket; i++) {
				System.out.println("How many products you want to add in basket " + i + "?");
				int numberOfProductsToBePurchased = Integer.parseInt(scanner.nextLine());
				System.out.println(
						"Please provide the detail of " + numberOfProductsToBePurchased + " product(s) line by line.");
				products = new ArrayList<>();
				for (int index = 0; index < numberOfProductsToBePurchased; index++) {
					String detail = scanner.nextLine();
					products.add(createProductFromInput(detail));
				}
				System.out.println("--------Tax details for basket " + i + "--------");

				TaxApplier taxApplier=new TaxApplierImpl(new TaxCalculatorImpl());
				Basket basket=new Basket(products);
				basket=taxApplier.applyTaxesAndCalculatePrice(basket);
				basket.printReceipt();
			}
		} catch (Exception e) {
			throw new SalesTaxException(e.getMessage());
		}
	}
	
	/**
	 * Method to create Product instance from string input.
	 * @param productDetail - Accepts product details as String.
	 * @return Returns Product instance.
	 */
	private static Product createProductFromInput(String productDetail) {
		String[] parts = productDetail.split(" ");
		if (parts.length >= 4) {
			Integer quantity = Integer.parseInt(parts[0]);
			Double price = Double.parseDouble(parts[parts.length - 1]);
			StringBuilder nameBuilder = new StringBuilder(parts[1]);
			for (int i = 2; i < parts.length - 2; i++) {
				nameBuilder.append(" ").append(parts[i]);
			}
			String name = nameBuilder.toString();
			Boolean isImported = name.contains(Constants.IMPORTED);
			Category category = Stream.of(Constants.BOOK, Constants.CHOCOLATE, Constants.HEADACHE_PILLS).anyMatch(
					taxExempted -> name.contains(taxExempted)) ? Category.TAX_EXEMPTED : Category.NON_TAX_EXEMPTED;
			return new Product(quantity, name, price, isImported, category);
		} else {
			throw new SalesTaxException(
					"Invalid Input.\nInput Samples:\n1 book at 12.49\n1 imported box of chocolates at 10.00");
		}
	}
}
