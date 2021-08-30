package com.Common;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SMTPSender {
	public SMTPSender(String senderID, String senderPW, String recipientAddress, String _subject, String _body) {
		String host = //"smtp.daum.net";
						"smtp.naver.com";
		
		final String username = senderID;
		final String password = senderPW;
		int port = 465;
		
		String recipient = recipientAddress;
		String subject = _subject;
		String body = _body;
		
		Properties props = System.getProperties();
		
		if(!(recipient == null) && !recipient.equals("")) {
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", port);
			props.put("mail.smtp.auth",true);
			props.put("mail.smtp.ssl.enable", true);
			props.put("mail.smtp.ssl.trust",host);
		
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				String un = username;
				String pw = password;
				@Override
				protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
					return new javax.mail.PasswordAuthentication(un, pw);
				}
			});
			
			session.setDebug(false);
			
			Message mimeMessage = new MimeMessage(session);
			
			try {
			mimeMessage.setFrom(new InternetAddress(senderID + "@naver.com"));		// repeat to type sender email
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			
			mimeMessage.setSubject(subject);
			mimeMessage.setText(body);
			Transport.send(mimeMessage);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Failed to send email....");
		}
	}
}
