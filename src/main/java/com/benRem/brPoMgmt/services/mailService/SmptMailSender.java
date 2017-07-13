package com.benRem.brPoMgmt.services.mailService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.benRem.brPoMgmt.domain.Customer;
import com.benRem.brPoMgmt.domain.PurchaseOrderLineItem;
import com.benRem.brPoMgmt.reqResObj.ContactDetails;
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
 public void sendSimpleMail(ContactDetails toMail)
            throws MessagingException {
	 try {
	  HtmlEmail email = new HtmlEmail();
	    String authuser = "debnath.chaterje@gmail.com";
	    String authpwd = "deb-2011leena25";
	    email.setSmtpPort(587);
	   // email.setAuthenticator(new DefaultAuthenticator(authuser, authpwd));
	    email.setAuthentication(authuser, authpwd);
	    email.setDebug(true);
	    email.setHostName("smtp.gmail.com");
	   
			email.setFrom(authuser, "debnath");
		
	    email.setSubject("TestMail");
	    email.setHtmlMsg("<html><body><h4>welcome to Bengal Remedies " +toMail.getName()+" contacts you with mobile number "+toMail.getPhoneNo() +" and given a comments : "+toMail.getComments()+" and his mail id is :"+ toMail.getEmail()+"</h4></body></html>");
	    email.addTo(authuser, toMail.getName());
	    email.setTLS(true);
	    //https://www.google.com/settings/security/lesssecureapps turn it off. to send the mail
	    email.send();
	    } catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 }



	public void sendMailForOrder(Customer cust, List<PurchaseOrderLineItem> lineItem)
			throws MessagingException {
		try {
			HtmlEmail email = new HtmlEmail();
			String authuser = "debnath.chaterje@gmail.com";
			String authpwd = "deb-2011leena25";
			email.setSmtpPort(587);
			// email.setAuthenticator(new DefaultAuthenticator(authuser, authpwd));
			email.setAuthentication(authuser, authpwd);
			email.setDebug(true);
			email.setHostName("smtp.gmail.com");

			email.setFrom(authuser, "debnath");

			email.setSubject("TestMail");
			email.setHtmlMsg("<html><body><h4>welcome to Bengal Remedies " +cust.getCustomerName()+
					" contacts you with mobile number "+cust.getCustPrimeryPhone() +" and his mail id is :"
					+ cust.getCustEmail()+"</h4></body></html>");
			email.addTo(authuser, cust.getCustomerName());
			email.setTLS(true);
			//https://www.google.com/settings/security/lesssecureapps turn it off. to send the mail
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
