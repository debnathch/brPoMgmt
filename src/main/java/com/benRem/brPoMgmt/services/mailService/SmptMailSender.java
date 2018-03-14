package com.benRem.brPoMgmt.services.mailService;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.benRem.brPoMgmt.dao.PurchaeOrderDao;
import com.benRem.brPoMgmt.domain.Customer;
import com.benRem.brPoMgmt.domain.PurchaseOrderLineItem;
import com.benRem.brPoMgmt.reqResObj.ContactDetails;
import com.benRem.brPoMgmt.reqResObj.response.CartProduct;
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
private	PurchaeOrderDao purchaseOrderDao;


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

			email.setSubject("order mail :"+ lineItem.get(0).getPoId());
			StringBuffer orderTable = new StringBuffer();
			orderTable.append("<tr>")
					//.append("<td>").append(" Company ").append("</td>")
					.append("<td>").append("   ProductName   ").append("</td>")
					.append("<td>").append("   PackSize   ").append("</td>")
					.append("<td>").append("   Quantity   ").append("</td>")
					.append("<td>").append("     Description   ").append("</td>")
					.append("</tr>");
			for(CartProduct eachProduct : purchaseOrderDao.fetchCartDetails("10")) {
				orderTable.append("<tr>")
						//.append("<td>").append(" ").append(eachProduct.getCompany()).append(" ").append("</td>")
						.append("<td>").append(" ").append(eachProduct.getProduct_name()).append("     ").append("</td>")
						.append("<td>").append(" ").append(eachProduct.getProduct_pack_size()).append("     ").append("</td>")
						.append("<td>").append(" ").append(eachProduct.getProduct_qty()).append("       ").append("</td>")
						.append("<td>").append(" ").append(eachProduct.getProduct_description()).append("     ").append("</td>")
						.append("</tr>");


			}
			email.setHtmlMsg("<html><body><h4>welcome to Bengal Remedies " +cust.getCustomerName()+
					" contacts you with mobile number "+cust.getCustPrimeryPhone() +" and his mail id is :"
					+ cust.getCustEmail()+"</h4>"+
							orderTable+
					"</body></html>");
			email.addTo(cust.getCustEmail(), cust.getCustEmail());
			email.setTLS(true);
			//https://www.google.com/settings/security/lesssecureapps turn it off. to send the mail
			email.send();
			purchaseOrderDao.updateCartToOrder(lineItem.get(0).getPoId());
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
