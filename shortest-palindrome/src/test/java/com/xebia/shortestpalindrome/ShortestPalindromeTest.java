package com.xebia.shortestpalindrome;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ShortestPalindromeTest {
	private ShortestPalindrome shortestPalindrome;

	@Before
	public void setup() {
		shortestPalindrome = new ShortestPalindrome();
	}

	@Test
	public void makePalindromeTest1() {
		assertEquals("palinilap", shortestPalindrome.makePalindrome("palin"));
	}

	@Test
	public void makePalindromeTest2() {
		assertEquals("a", shortestPalindrome.makePalindrome("a"));
	}

	@Test
	public void makePalindromeTest3() {
		assertEquals("aba", shortestPalindrome.makePalindrome("ab"));
	}

	@Test
	public void makePalindromeTest4() {
		assertEquals("aba", shortestPalindrome.makePalindrome("aba"));
	}

	@Test
	public void makePalindromeTest5() {
		assertEquals("Invalid String Input. String cannot be null or blank!", shortestPalindrome.makePalindrome(""));
	}

	@Test
	public void makePalindromeTest6() {
		assertEquals("Invalid String Input. String cannot be null or blank!", shortestPalindrome.makePalindrome(null));
	}
}
