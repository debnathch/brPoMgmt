package com.benRem.brPoMgmt.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.benRem.brPoMgmt.dao.PurchaeOrderDao;
import com.benRem.brPoMgmt.domain.Br_Product;
import com.benRem.brPoMgmt.services.SmptMailSender;

import groovy.util.logging.Slf4j;

@Component
@RestController
@Slf4j
public class MasterController {
	
	@Autowired
	PurchaeOrderDao purchaseOrderDao;
	  
	  @Autowired
	  SmptMailSender smptp;
//	
//	 public String greeting(Model model,@RequestParam(value="name", defaultValue="World") String name)  {
//	        System.out.println("****** Debnath *****");
//	        model.addAttribute("name", name);
//	        return "hello";
//	    }
	
	 @RequestMapping(value ="/", method = RequestMethod.GET)
	 public RedirectView greeting()  {
	        System.out.println("****** Debnath *****");
	        return new RedirectView("indexBr.html");
	    }
	
	 @RequestMapping("/home")
	 public String home()  {
	        System.out.println("****** Debnath home *****");
//	        for(Purchase_Order poOrder : purchaseOrderDao.FindPurchaseOrder()){
//	        	System.out.println(poOrder + " order");
//	        }
//	        
	        for(Br_Product brProduct : purchaseOrderDao.FindProductList()){
	        	System.out.println(brProduct + " product");
	        }
	        return "Success";
	    }
	 
	 @RequestMapping(value ="/submitContactForm", method = RequestMethod.POST)
	 public RedirectView contactUs(){
		 mainSend();
		 return new RedirectView("indexBr.html");
	 }
	 
	 private void mainSend(){
		  
		  try {
			smptp.sendSimpleMail();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
