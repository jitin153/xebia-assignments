package com.xebia.sendmail;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

public class MailSenderTest {
	private MailSender mailSender = mock(MailSender.class);
	private String[][] friendsArray = { { "rahul@test.com", "rajat@test.com,rashmi@test.com,vinod@test.com" },
			{ "vineet@test.com", "ajay@test.com,rajat@test.com,rahul@test.com" },
			{ "vinod@test.com", "rahul@test.com,nitin@test.com,vineet@test.com" } };

	@Test
	public void sendMailTest1() {
		mailSender.sendMail("rahul@test.com", "Test Mail", friendsArray);
		Mockito.verify(mailSender, times(1)).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.any());
	}

	@Test
	public void sendMailTest2() {
		Mockito.doAnswer((Answer) invocation -> {
			Object arg0 = invocation.getArgument(0);
			Object arg1 = invocation.getArgument(1);
			Object arg2 = invocation.getArgument(2);
			assertEquals("rahul@test.com", arg0);
			assertEquals("Test Mail", arg1);
			assertEquals(friendsArray, arg2);
			return null;
		}).when(mailSender).sendMail(Mockito.anyString(), Mockito.anyString(), Mockito.any());
		mailSender.sendMail("rahul@test.com", "Test Mail", friendsArray);
	}
}
