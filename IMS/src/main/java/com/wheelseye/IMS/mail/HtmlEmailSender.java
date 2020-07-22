package com.wheelseye.IMS.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class HtmlEmailSender {

	public void sendHtmlEmail(String toAddress, String subject, String message) throws AddressException, MessagingException {
		//SMTP Server Information
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "shishpal.nitsri@gmail.com";
		String passwod = "****";
		
		final String userName = mailFrom;
		final String password = passwod;

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		// set plain text message
		msg.setContent(message, "text/html");

		// sends the e-mail
		Transport.send(msg);
	}

	/**
	 * Test the send html e-mail method
	 *
	 */
	public static void main(String[] args) {
		// outgoing message information
		String mailTo = "saurabh.verma@wheelseye.com";
		String subject = "Template Email";

		// message contains HTML markups
		String message = "<i>Greetings!</i><br>";
		message += "<b>Wish you a nice day!</b><br>";
		message += "Find Attached Resume:";
		message += "<a href=\"https://www.linkedin.com/in/shishpal/detail/overlay-view/urn:li:fsd_profileTreasuryMedia:(ACoAACLVXvMBsGmaMr-MsaFtlOKwOmyppAhz004,1595175123029)/\" \">Link</a>";
		message += "<br>";
		message += "<font color=red>Shish Pal</font>";

		HtmlEmailSender mailer = new HtmlEmailSender();

		try {
			mailer.sendHtmlEmail(mailTo, subject, message);
			System.out.println("Email Successfully Sent.");
		} catch (Exception ex) {
			System.out.println("Failed to sent email.");
			ex.printStackTrace();
		}
	}
}