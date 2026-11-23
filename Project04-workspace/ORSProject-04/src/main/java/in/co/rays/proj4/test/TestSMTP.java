
package in.co.rays.proj4.test;

import java.util.HashMap;

import in.co.rays.proj4.util.EmailBuilder;
import in.co.rays.proj4.util.EmailMessage;
import in.co.rays.proj4.util.EmailUtility;

/**
 * TestSMTP class is used to test SMTP email functionality.
 * It sends a user registration email using EmailUtility.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class TestSMTP {

	/**
	 * Main method to execute the SMTP email test.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {

		testUserRegistrationMail();

	}

	/**
	 * Tests the user registration email functionality.
	 * Creates an email message and sends it to the specified user.
	 */
	public static void testUserRegistrationMail() {

		HashMap<String, String> map = new HashMap<String, String>();
		EmailMessage msg = new EmailMessage();

		map.put("login", "nirmalfayake1307@gmail.com");
		map.put("password", "abhy123");

		msg.setTo(map.get("login"));
		msg.setSubject("User Rgistration Information");
		msg.setMessage(EmailBuilder.getUserRegistrationMessage(map));
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		System.out.println("mail send successfully");

	}

}
