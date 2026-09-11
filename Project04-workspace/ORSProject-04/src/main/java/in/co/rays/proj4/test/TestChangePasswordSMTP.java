package in.co.rays.proj4.test;

import java.util.HashMap;

import in.co.rays.proj4.util.EmailBuilder;
import in.co.rays.proj4.util.EmailMessage;
import in.co.rays.proj4.util.EmailUtility;

public class TestChangePasswordSMTP {

	
	public static void main(String[] args) {

		testChangePasswordMail();
	}

	public static void testChangePasswordMail() {
		
		HashMap<String, String> map = new HashMap<String, String>();
		EmailMessage msg = new EmailMessage();
		
		map.put("login", "singh1223@protonmail.com");
		map.put("firstName", "piysuh");
		map.put("lastName", "singh");
		map.put("password", "abhay@123");
		
		msg.setTo(map.get("login"));
		msg.setSubject("User Change password");
		msg.setMessage(EmailBuilder.getChangePasswordMessage(map));
		msg.setMessageType(EmailMessage.HTML_MSG);
		
		EmailUtility.sendMail(msg);
		System.out.println("Change Password mail send successfully");
		
	}
}
