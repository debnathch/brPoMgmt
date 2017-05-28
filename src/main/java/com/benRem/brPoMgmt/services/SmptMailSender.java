package com.benRem.brPoMgmt.services;

import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class SmptMailSender {

@Autowired
private JavaMailSender javaMailSender;  

 @Autowired 
 private TemplateEngine templateEngine;
 public void sendSimpleMail() 
            throws MessagingException {
	 try {
	  HtmlEmail email = new HtmlEmail();
	    String authuser = "debnathch@gmail.com";
	    String authpwd = "deb-2011leena261";
	    email.setSmtpPort(587);
	   // email.setAuthenticator(new DefaultAuthenticator(authuser, authpwd));
	    email.setAuthentication(authuser, authpwd);
	    email.setDebug(true);
	    email.setHostName("smtp.gmail.com");
	   
			email.setFrom("debnathch@gmail.com", "debnath");
		
	    email.setSubject("TestMail");
	    email.setHtmlMsg("<html><body><h1>welcome to Bengal Remedies" +" Debnath"+"contacts you with mobile number "+"9836711699"+"</h1></body></html>");
	    email.addTo("debaleenab@gmail.com", "debaleena ");
	    email.setTLS(true);
	    //https://www.google.com/settings/security/lesssecureapps turn it off. to send the mail
	    email.send();
	    } catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 }
}
