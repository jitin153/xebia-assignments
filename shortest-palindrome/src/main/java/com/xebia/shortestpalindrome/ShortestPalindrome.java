package com.xebia.shortestpalindrome;

/**
 * <b>ShortestPalindrome</b> Class contains method to create palindrome string
 * if not.
 * 
 * @author Jitin Kumar
 *
 */
public class ShortestPalindrome {
	public static void main(String[] args) {
		ShortestPalindrome shortestPalindrome = new ShortestPalindrome();
		System.out.println(shortestPalindrome.makePalindrome("palin"));
		System.out.println(shortestPalindrome.makePalindrome("a"));
		System.out.println(shortestPalindrome.makePalindrome("ab"));
		System.out.println(shortestPalindrome.makePalindrome("aba"));
	}

	/**
	 * Method to create input string palindrome if not.
	 * 
	 * @param stringToMakePalindrome
	 *            - Accepts String whose palindrome to be created.
	 */
	public String makePalindrome(String stringToMakePalindrome) {
		if (null != stringToMakePalindrome && !stringToMakePalindrome.isEmpty()) {
			if (isAlreadyPalindrome(stringToMakePalindrome)) {
				/*
				 * Checking if the input string is already a palindrome if that is the case then
				 * no need to go further. We can return same string from here.
				 */
				return stringToMakePalindrome;
			} else {
				StringBuilder temporaryString = new StringBuilder();
				char[] characters = stringToMakePalindrome.toCharArray();
				int i = 0;
				int j = characters.length - 1;
				while (i != j) {
					if (characters[i] != characters[j]) {
						temporaryString.insert(0, characters[i]);
					} else {
						temporaryString.insert(0, characters[j--]);
					}
					i++;
				}
				String resultedString = stringToMakePalindrome.substring(0, i + 1) + temporaryString;
				return resultedString;
			}
		} else {
			return "Invalid String Input. String cannot be null or blank!";
		}
	}

	/**
	 * Method to check whether given string is a palindrome or not.
	 * 
	 * @param stringToCheckForPalindrome
	 *            - Accepts String to be checked for palindrome.
	 * @return Returns true if given string is palindrome false otherwise.
	 */
	private static boolean isAlreadyPalindrome(String stringToCheckForPalindrome) {
		return stringToCheckForPalindrome
				.equalsIgnoreCase(new StringBuilder(stringToCheckForPalindrome).reverse().toString());
	}
}
