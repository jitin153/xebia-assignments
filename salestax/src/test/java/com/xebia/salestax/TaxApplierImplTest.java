package com.xebia.salestax;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.xebia.salestax.exception.SalesTaxException;
import com.xebia.salestax.model.Basket;
import com.xebia.salestax.model.Category;
import com.xebia.salestax.model.Product;

public class TaxApplierImplTest {

	private TaxApplier taxApplier;
	private TaxCalculator taxCalculator = mock(TaxCalculatorImpl.class);

	@Before
	public void setup() {
		taxApplier = new TaxApplierImpl(taxCalculator);
	}

	@Test
	public void applyTaxAndCalculatePriceTest1() {
		Basket basket = new Basket(new HashSet<>(Arrays.asList(new Product(1, "book", 12.47, false, Category.TAX_EXEMPTED),
				new Product(1, "music CD", 14.99, false, Category.NON_TAX_EXEMPTED),
				new Product(1, "chocolate bar", 0.85, false, Category.TAX_EXEMPTED))));
		taxApplier.applyTaxesAndCalculatePrice(basket);
		Mockito.verify(taxCalculator, times(3)).calculateTax(Mockito.any());
	}

	@Test
	public void applyTaxAndCalculatePriceTest2() {
		Basket basket = new Basket(
				new HashSet<>(Arrays.asList(new Product(1, "imported box of chocolates", 10.00, true, Category.TAX_EXEMPTED),
						new Product(1, "imported bottle of perfume", 47.50, true, Category.NON_TAX_EXEMPTED))));
		taxApplier.applyTaxesAndCalculatePrice(basket);
		Mockito.verify(taxCalculator, times(2)).calculateTax(Mockito.any());
	}

	@Test
	public void applyTaxAndCalculatePriceTest3() {
		Basket basket = new Basket(
				new HashSet<>(Arrays.asList(new Product(1, "imported bottle of perfume", 27.99, true, Category.NON_TAX_EXEMPTED),
						new Product(1, "bottle of perfume", 18.99, false, Category.NON_TAX_EXEMPTED),
						new Product(1, "packet of headache pills", 9.75, false, Category.TAX_EXEMPTED),
						new Product(1, "box of imported chocolates", 11.25, true, Category.TAX_EXEMPTED))));
		taxApplier.applyTaxesAndCalculatePrice(basket);
		Mockito.verify(taxCalculator, times(4)).calculateTax(Mockito.any());
	}

	@Test
	public void applyTaxAndCalculatePriceTest4() {
		List<Product> products=Arrays.asList(new Product(1, "book", 12.49, false, Category.TAX_EXEMPTED),
				new Product(1, "music CD", 14.99, false, Category.NON_TAX_EXEMPTED),
				new Product(1, "chocolate bar", 0.85, false, Category.TAX_EXEMPTED));
		Basket basket = new Basket(new HashSet<>(products));
		
		when(taxCalculator.calculateTax(Matchers.refEq(products.get(0)))).thenReturn(new Double(0.0));
		when(taxCalculator.calculateTax(Matchers.refEq(products.get(1)))).thenReturn(new Double(1.49));
		when(taxCalculator.calculateTax(Matchers.refEq(products.get(2)))).thenReturn(new Double(0.0));
		
		Basket basketFromActualServiceCall = taxApplier.applyTaxesAndCalculatePrice(basket);
		assertNotNull(basketFromActualServiceCall);
		assertEquals(new Double(1.5), basketFromActualServiceCall.getSalesTax());
		assertEquals(new Double(29.83), basketFromActualServiceCall.getTotalPrice());

	}
	
	@Test
	public void applyTaxAndCalculatePriceTest5() {
		List<Product> products=Arrays.asList(new Product(1, "imported box of chocolates", 10.00, true, Category.TAX_EXEMPTED),
				new Product(1, "imported bottle of perfume", 47.50, true, Category.NON_TAX_EXEMPTED));
		Basket basket = new Basket(new HashSet<>(products));
		taxApplier.applyTaxesAndCalculatePrice(basket);
		
		when(taxCalculator.calculateTax(Matchers.refEq(products.get(0)))).thenReturn(new Double(0.5));
		when(taxCalculator.calculateTax(Matchers.refEq(products.get(1)))).thenReturn(new Double(7.125));
		
		Basket basketFromActualServiceCall = taxApplier.applyTaxesAndCalculatePrice(basket);
		assertNotNull(basketFromActualServiceCall);
		assertEquals(new Double(7.65), basketFromActualServiceCall.getSalesTax());
		assertEquals(new Double(65.15), basketFromActualServiceCall.getTotalPrice());

	}
	
	@Test
	public void applyTaxAndCalculatePriceTest6() {
		List<Product> products=Arrays.asList(new Product(1, "imported bottle of perfume", 27.99, true, Category.NON_TAX_EXEMPTED),
				new Product(1, "bottle of perfume", 18.99, false, Category.NON_TAX_EXEMPTED),
				new Product(1, "packet of headache pills", 9.75, false, Category.TAX_EXEMPTED),
				new Product(1, "box of imported chocolates", 11.25, true, Category.TAX_EXEMPTED));
		Basket basket = new Basket(new HashSet<>(products));
		taxApplier.applyTaxesAndCalculatePrice(basket);
		
		when(taxCalculator.calculateTax(Matchers.refEq(products.get(0)))).thenReturn(new Double(4.198499999999999));
		when(taxCalculator.calculateTax(Matchers.refEq(products.get(1)))).thenReturn(new Double(1.8989999999999998));
		when(taxCalculator.calculateTax(Matchers.refEq(products.get(2)))).thenReturn(new Double(0.0));
		when(taxCalculator.calculateTax(Matchers.refEq(products.get(3)))).thenReturn(new Double(0.5625));
		
		Basket basketFromActualServiceCall = taxApplier.applyTaxesAndCalculatePrice(basket);
		assertNotNull(basketFromActualServiceCall);
		assertEquals(new Double(6.70), basketFromActualServiceCall.getSalesTax());
		assertEquals(new Double(74.68), basketFromActualServiceCall.getTotalPrice());

	}
	
	@Test
	public void applyTaxAndCalculatePriceTest7() {
		Basket basket = new Basket(new HashSet<>(Arrays.asList(new Product(1, "book", 12.49, false, Category.TAX_EXEMPTED),
				new Product(1, "chocolate bar", 0.85, false, Category.TAX_EXEMPTED))));
		taxApplier.applyTaxesAndCalculatePrice(basket);
		
		when(taxCalculator.calculateTax(Mockito.isA(Product.class))).thenReturn(new Double(0.0));
		
		Basket basketFromActualServiceCall = taxApplier.applyTaxesAndCalculatePrice(basket);
		assertNotNull(basketFromActualServiceCall);
		assertEquals(new Double(0.0), basketFromActualServiceCall.getSalesTax());
		assertEquals(new Double(13.34), basketFromActualServiceCall.getTotalPrice());

	}
	
	@Test(expected=SalesTaxException.class)
	public void applyTaxAndCalculatePriceTest8() {
		Basket basket = new Basket(null);
		taxApplier.applyTaxesAndCalculatePrice(basket);
		
		when(taxCalculator.calculateTax(Mockito.isA(Product.class))).thenThrow(new SalesTaxException("Empty Product List."));		
		fail();

	}
}
