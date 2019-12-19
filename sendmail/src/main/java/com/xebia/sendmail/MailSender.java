package com.xebia.sendmail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <b>MailSender</b> Class contains method to send mail to the network of
 * friends.
 * 
 * @author Jitin Kumar
 *
 */
public class MailSender {

	public static void main(String[] args) {
		String[][] friendsArray = { { "rahul@test.com", "rajat@test.com,rashmi@test.com,vinod@test.com" },
				{ "vineet@test.com", "ajay@test.com,rajat@test.com,rahul@test.com" },
				{ "vinod@test.com", "rahul@test.com,nitin@test.com,vineet@test.com" } };
		MailSender mailSender = new MailSender();
		mailSender.sendMail("rahul@test.com", "Test Mail!", friendsArray);
	}

	public void sendMail(String emailAddress, String message, String[][] friendsArray) {
		if (null != emailAddress && !emailAddress.isEmpty() && friendsArray.length > 0) {
			/*
			 * Creating map from array.
			 */
			Map<String, List<String>> friendsMap = new HashMap<>();
			for (int i = 0; i < friendsArray.length; i++) {
				/*
				 * Performing trim on each email in case comma separated emails has space in
				 * between. rajat@test.com, rashmi@test.com, vinod@test.com --To handle this
				 * situation.
				 */
				friendsMap.put(friendsArray[i][0], Arrays.asList(friendsArray[i][1].split(",")).stream()
						.map(name -> name.trim()).collect(Collectors.toList()));
			}
			Set<String> recipients = new HashSet<>();
			recipients = getRecipients(emailAddress, friendsMap, recipients);
			recipients.remove(emailAddress);
			if (null != recipients && !recipients.isEmpty()) {
				MailSender mailSender = new MailSender();
				recipients.forEach(recipient -> {
					String customizedMessage = new StringBuilder("From : ").append(emailAddress).append("\nMessage : ")
							.append(message).append("\n").toString();
					mailSender.sendMail(recipient, customizedMessage);
				});
			} else {
				System.out.println("No recipient!");
			}
		} else {
			System.out.println("Invalid input. Please check your input & try again!");
		}
	}

	/**
	 * Method to send mail to all the friends in network
	 * 
	 * @param emailAddress
	 * @param message
	 */
	public void sendMail(String emailAddress, String message) {
		/*
		 * NOTE : Since these are the dummy mail IDs therefore I'm just printing To,
		 * From & Email here. I'm not trying to send the actual email here but if we
		 * want to do so we can leverage Java Mail API.
		 */
		System.out.println("To : " + emailAddress + "\n" + message);
	}

	/**
	 * Recursive method to find out the whole network of friends or friend.
	 * 
	 * @param senderEmail
	 * @param friendsMap
	 * @param recipients
	 * @return Returns set of recipients.
	 */
	private static Set<String> getRecipients(String senderEmail, Map<String, List<String>> friendsMap,
			Set<String> recipients) {
		List<String> friends = friendsMap.get(senderEmail);
		if (null != friends && !friends.isEmpty()) {
			friends.forEach(friend -> {
				if (!friend.isEmpty() && !recipients.contains(friend)) {
					recipients.add(friend);
					getRecipients(friend, friendsMap, recipients); // --Recursive call
				}
			});
		}
		return recipients;
	}
}
