package in.co.rays.proj4.test;

import in.co.rays.proj4.util.MessageSource;

/**
 * TestBundle class is used to test the MessageSource utility.
 * It sets the locale to English and displays the corresponding
 * message from the resource bundle.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class TestBundle {

	/**
	 * Main method to execute the TestBundle class.
	 * It initializes MessageSource, sets the locale to English
	 * and prints the login title message.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {

		MessageSource ms = MessageSource.getInstance();

		ms.setLocale("en");

		System.out.println(ms.get("login.title"));

	}

}