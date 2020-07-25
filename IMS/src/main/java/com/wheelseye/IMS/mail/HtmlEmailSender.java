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

	public void sendRoundEmail(String toAddress, String round, String jobPosition, String dt,
										String hrName, Long phnumber) throws AddressException, MessagingException {
		String subject = "Interview -WheelsEye";
		String sround = ""+round.charAt(14);
		
		
		// message contains HTML markups
		String message = "Hi,";
		message += "<br><br>";
		message += "Your round " + sround + " of Technical interview is scheduled with WheelsEye on "+dt;
		message += " for "+jobPosition+".";
		message += "<br><br>";
		message += "In case of any query feel free to reach out to me.\n";
		message += "<br><br>";
		message += "Sincerely,";
		message += "<br>";
		message += hrName;
		message += "<br>";
		message += phnumber;
		message += "<br>";
		message += "Wheelseye Technology Pvt. Ltd.";
		
		//SMTP Server Information
		String host = "smtp.gmail.com";
		String port = "587";
		final String mailFrom = "ims.app.wheelseye@gmail.com";
		final String password = "IMSapplication@123";

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailFrom, password);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(mailFrom));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setContent(message, "text/html");

		// sends the e-mail
		Transport.send(msg);
	}
	
	public void sendInterviewerMail(Long intervieweeid, String toAddress, String round, String jobPosition, String dt, 
			String hrName, Long phnumber) throws AddressException, MessagingException {
			String subject = "Interview -WheelsEye";
			String sround = ""+round.charAt(14);
			String resumelink = "http://localhost:8080/downloadFile/"+Long.toString(intervieweeid);


			// message contains HTML markups
			String message = "Hi,";
			message += "<br><br>";
			message += "Your round " + sround + " of Technical interview is scheduled with WheelsEye on "+dt;
			message += " for "+jobPosition+".";
			message += "<br><br>";
			message += "In case of any query feel free to reach out to me.\n";
			message += "<br><br>";
			message += "<a href = "+resumelink+">Resume Link </a>";
			message += "<br><br>";
			message += "Sincerely,";
			message += "<br>";
			message += hrName;
			message += "<br>";
			message += phnumber;
			message += "<br>";
			message += "Wheelseye Technology Pvt. Ltd.";

			//SMTP Server Information
			String host = "smtp.gmail.com";
			String port = "587";
			final String mailFrom = "ims.app.wheelseye@gmail.com";
			final String password = "IMSapplication@123";

			// sets SMTP server properties
			Properties properties = new Properties();
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", port);
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");

			// creates a new session with an authenticator
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(mailFrom, password);
				}
			};

			Session session = Session.getInstance(properties, auth);

			// creates a new e-mail message
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(mailFrom));
			InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
			msg.setRecipients(Message.RecipientType.TO, toAddresses);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setContent(message, "text/html");

			// sends the e-mail
			Transport.send(msg);
	}
	
	public void sendRejectMail(String toAddress, String hrName, String intervieweeName, String jobPosition) 
																			throws AddressException, MessagingException{
		String subject = "Thank you for your interest in a career with us";
		
		// message contains HTML markups
		String message = "Dear "+intervieweeName+",";
		message += "<br><br>";
		message += "Thank you for your interest in the "+jobPosition+". We regret to tell you that you are no longer being considered.";
		message += "<br><br>";
		message += "However, we encourage you to continue to visit our careers site to search and apply for other positions that match your skills and interests.";
		message += "<br><br>";
		message += "We appreciate the time you took to apply and wish you success in finding your next role.";
		message += "<br><br>";
		message += "Sincerely,";
		message += "<br>";
		message += hrName;
		message += "<br>";
		message += "Wheelseye Technology Pvt. Ltd.";
		
		//SMTP Server Information
		String host = "smtp.gmail.com";
		String port = "587";
		final String mailFrom = "ims.app.wheelseye@gmail.com";
		final String password = "IMSapplication@123";


		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailFrom, password);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(mailFrom));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setContent(message, "text/html");

		// sends the e-mail
		Transport.send(msg);
	}
	
	public void sendAcceptMail(String toAddress, String hrName, String intervieweeName, String jobPosition) 
																		throws AddressException, MessagingException{
		String subject = "Offer Letter -  "+intervieweeName;
		
		// message contains HTML markups
		String message = "Dear "+intervieweeName+",";
		message += "<br><br>";
		message += "Welcome aboard! We are very happy to have you at Wheelseye Technology Pvt. Ltd. as "+jobPosition;
		message += ". We are confident that your expertise and dedication can contribute significantly to the company.";
		message += "<br><br>";
		message += "Hope your stint with the Company will be valuable for both of us.";
		message += "<br><br>";
		message += "Sincerely,";
		message += "<br>";
		message += hrName;
		message += "<br>";
		message += "Wheelseye Technology Pvt. Ltd.";
		
		//SMTP Server Information
		String host = "smtp.gmail.com";
		String port = "587";
		final String mailFrom = "ims.app.wheelseye@gmail.com";
		final String password = "IMSapplication@123";

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailFrom, password);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(mailFrom));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setContent(message, "text/html");

		// sends the e-mail
		Transport.send(msg);
	}
}