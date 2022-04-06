package com.smart.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	public boolean sendEmail(String subject, String message, String to) {
		//rest of code..
		
		boolean f = false;
		
		String from="pirkashom662@gmail.com";
		
		//variable for gmail
		String host = "smtp.gmail.com";
		
		//get the System properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES "+properties);
		//setting import
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth", "true");
		
		//setp 1: to get session object..
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("pirkashom662@gmail.com", "Kirshna!12");
			}
		
		
		});
		session.setDebug(true);
		
		//setp 2: compose the message [text, multi media]
		MimeMessage m = new MimeMessage(session);
		
		try {
			
			//from mail
			m.setFrom(from);
			
			//adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//adding subject to message
			m.setSubject(subject);
			
			//adding text to message
			//m.setText(message);
			m.setContent(message, "text/html");
			
			//send
			
			//step 3: send the message using Transport class
			Transport.send(m);
			
			System.out.println("Sent Success............");
			f=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
