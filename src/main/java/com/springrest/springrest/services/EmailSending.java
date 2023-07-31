package com.springrest.springrest.services;

import java.io.File;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springrest.springrest.validator.EmailValidation;


@Service
public class EmailSending {
	
	
	
	  boolean flag=true;
	
	public boolean sendEmail(String from,String to,String subject,String message,String fileUrl)
	{
		
	

		// using host as localhost
		String host = "smtp.gmail.com";

		// Getting system properties
		Properties properties = System.getProperties();

		// Setting up mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.starttls.enable", "true");
	     properties.setProperty("mail.smtp.auth", "true");
	     
	    
		

      
    	
	     Session session = Session.getDefaultInstance(properties, 
	  			    new javax.mail.Authenticator(){
	  			        protected PasswordAuthentication getPasswordAuthentication() {
	  			            return new PasswordAuthentication(
	  			                from, "vvbnquiclyktvcfw");// Specify the Username and the PassWord
	  			        }
	  			        
	  			});
	  		
    	 
    	
	   
 
		
		
		

		try
		{
			Transport transport = session.getTransport();  
			// MimeMessage object.
			MimeMessage msg = new MimeMessage(session);

			// Set From Field: adding senders email to from field.
			msg.setFrom(new InternetAddress(from));

			// Set To Field: adding recipient's email to from field.
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: subject of the email
			msg.setSubject(subject);

			// set body of the email.
			
	

			MimeMultipart mimeMultiPart=new MimeMultipart();
			
			MimeBodyPart textMime=new MimeBodyPart();
			MimeBodyPart fileMime=new MimeBodyPart();
			
			try {
				File file=new File(fileUrl);
				
				
				
				textMime.setText(message);
				
				
				fileMime.attachFile(file);
				
				mimeMultiPart.addBodyPart(textMime);
				mimeMultiPart.addBodyPart(fileMime);
				
				
				
			}
			
			
			catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
			
		msg.setContent(mimeMultiPart);
			
			Transport.send(msg);

			// Send email.
			
			
//			Transport.send(message);
			System.out.println("Mail successfully sent");
			return true;
		}
		
		catch (Exception mex)
		{
			mex.printStackTrace();
			return false;
		}
		
		
	}
}

