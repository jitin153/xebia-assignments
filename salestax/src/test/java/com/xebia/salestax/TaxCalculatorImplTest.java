package com.xebia.salestax;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import com.xebia.salestax.model.Category;
import com.xebia.salestax.model.Product;

public class TaxCalculatorImplTest {
	private TaxCalculator taxCalculator;
	
	@Before
    public void setup() {
		taxCalculator=new TaxCalculatorImpl();
    }
	
	@Test
	public void calculateTaxTest1() {
		Product product=new Product(1,"book",12.47,false,Category.TAX_EXEMPTED);
		Double tax=taxCalculator.calculateTax(product);
		assertNotNull(tax);
		assertEquals(new Double(0.0),tax);
	}
	
	@Test
	public void calculateTaxTest2() {
		Product product=new Product(1,"music CD",14.99,false,Category.NON_TAX_EXEMPTED);
		Double tax=taxCalculator.calculateTax(product);
		assertNotNull(tax);
		assertEquals(new Double(1.499),tax);
	}
	
	@Test
	public void calculateTaxTest3() {
		Product product=new Product(1,"chocolate bar",0.85,false,Category.TAX_EXEMPTED);
		Double tax=taxCalculator.calculateTax(product);
		assertNotNull(tax);
		assertEquals(new Double(0.0),tax);
	}
	
	@Test
	public void calculateTaxTest4() {
		Product product=new Product(1,"imported box of chocolates",10.00,true,Category.TAX_EXEMPTED);
		Double tax=taxCalculator.calculateTax(product);
		assertNotNull(tax);
		assertEquals(new Double(0.5),tax);
	}
	
	@Test
	public void calculateTaxTest5() {
		Product product=new Product(1,"imported bottle of perfume",47.50,true,Category.NON_TAX_EXEMPTED);
		Double tax=taxCalculator.calculateTax(product);
		assertNotNull(tax);
		assertEquals(new Double(7.125),tax);
	}
	
	@Test
	public void calculateTaxTest6() {
		Product product=new Product(1,"packet of headache pills",9.75,false,Category.TAX_EXEMPTED);
		Double tax=taxCalculator.calculateTax(product);
		assertNotNull(tax);
		assertEquals(new Double(0.0),tax);
	}
	
	@Test
	public void calculateTaxTest7() {
		Double tax=taxCalculator.calculateTax(null);
		assertNotNull(tax);
		assertEquals(new Double(0.0),tax);
	}
}
