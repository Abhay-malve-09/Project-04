package in.co.rays.proj4.test;

import java.util.HashMap;

import in.co.rays.proj4.util.EmailBuilder;
import in.co.rays.proj4.util.EmailMessage;
import in.co.rays.proj4.util.EmailUtility;

public class TestForgetSMTP {

	public static void main(String[] args) {

		testForgetMail();
	}

	public static void testForgetMail() {
		
		HashMap<String, String> map = new HashMap<String, String>();
		EmailMessage msg = new EmailMessage();
		
		map.put("login", "singh1223@protonmail.com");
		map.put("firstName", "piysuh");
		map.put("lastName", "singh");
		map.put("password", "abhay@123");
		
		msg.setTo(map.get("login"));
		msg.setSubject("User forget password");
		msg.setMessage(EmailBuilder.getForgetPasswordMessage(map));
		msg.setMessageType(EmailMessage.HTML_MSG);
		
		EmailUtility.sendMail(msg);
		System.out.println("forget mail send successfully");
		
	}

}
