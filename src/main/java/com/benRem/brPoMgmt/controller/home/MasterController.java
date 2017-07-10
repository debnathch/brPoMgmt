package com.benRem.brPoMgmt.controller.home;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.benRem.brPoMgmt.dao.ProductDao;
import com.benRem.brPoMgmt.dao.PurchaeOrderDao;
import com.benRem.brPoMgmt.reqResObj.response.AjaxResponseBody;
import com.benRem.brPoMgmt.reqResObj.ContactDetails;
import com.benRem.brPoMgmt.reqResObj.OrderItem;
import com.benRem.brPoMgmt.reqResObj.response.Products;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.benRem.brPoMgmt.domain.Br_Product;
import com.benRem.brPoMgmt.services.mailService.SmptMailSender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RestController
@Slf4j


public class MasterController {
	
	@Autowired
	PurchaeOrderDao purchaseOrderDao;

	@Autowired
	ProductDao productDao;



	  
	  @Autowired
	  SmptMailSender smptp;

//
//	 public String greeting(Model model,@RequestParam(value="name", defaultValue="World") String name)  {
//	        System.out.println("****** Debnath *****");
//	        model.addAttribute("name", name);
//	        return "hello";
//	    }
	
	 @RequestMapping(value ="/home", method = RequestMethod.GET)
	 public RedirectView greeting()  {
	        System.out.println("****** entry to website *****");

		 return new RedirectView("start.html");
	    }


	@RequestMapping(value ="/addCart", method = RequestMethod.POST)
	public ResponseEntity<?> addToCart(@Valid @RequestBody OrderItem orderItem)  {
		System.out.println(orderItem.getProd_id()+"****** entry to cart *****"+orderItem.getProd_qty());
		AjaxResponseBody result = new AjaxResponseBody();

		// add to cart to table
		if(purchaseOrderDao.saveToCart(orderItem).equalsIgnoreCase("success"))
			return ResponseEntity.ok(result) ;
		else
			return ResponseEntity.ok(HttpStatus.EXPECTATION_FAILED);

	}

	@RequestMapping(value ="/triggerToOrder", method = RequestMethod.POST)
	public ResponseEntity<?> triggerToOrder(@Valid @RequestBody OrderItem orderItem)  {

		AjaxResponseBody result = new AjaxResponseBody();

		// add to cart to table
		if(purchaseOrderDao.triggerForOrder(orderItem).equalsIgnoreCase("success"))
			return ResponseEntity.ok(result) ;
		else
			return ResponseEntity.ok(HttpStatus.EXPECTATION_FAILED);

	}

	@RequestMapping(value ="/fetchFromCart", method = RequestMethod.POST)
	public List<Products> fetchFromCart(@Valid @RequestBody String customerId) throws IOException {


		AjaxResponseBody result = new AjaxResponseBody();

			System.out.println("****** fetching from cart  *****");
			List<Products> productList = new ArrayList<>();
			ObjectMapper objMapper = new ObjectMapper();
			purchaseOrderDao.findFromCart(customerId);

			for(Br_Product eachProduct : productDao.findItems()) {

				productList.add(objMapper.readValue(eachProduct.toString(), Products.class));
			}
		return  productList;

	}


	@RequestMapping(value ="/login", method = RequestMethod.GET)
	public RedirectView login()  {
		System.out.println("****** entry to website *****");

		return new RedirectView("productShop/login.html");
	}


	@RequestMapping(value ="/welcome", method = RequestMethod.GET)
	public RedirectView welcome()  {
		System.out.println("****** landed to home page*****");
		return new RedirectView("index.html");
	}

	@RequestMapping(value ="/productShop", method = RequestMethod.GET)
	public RedirectView productGallery()  {
		System.out.println("****** landed to shoping page*****");
		return new RedirectView("productShop/indexShop.html");
	}

	 @RequestMapping(value="/product", method = RequestMethod.GET)
	 public List<Products> orderPage() throws IOException {

	        System.out.println("****** Landing product page *****");
		 List<Products> productList = new ArrayList<>();
		 ObjectMapper objMapper = new ObjectMapper();
	        for(Br_Product eachProduct : productDao.findItems()) {

				productList.add(objMapper.readValue(eachProduct.toString(), Products.class));
			}

		 	return  productList;


	    }
	 
	 @RequestMapping(value ="/contactUs", method = RequestMethod.POST)
	 public ResponseEntity<?> contactUs(@Valid @RequestBody ContactDetails contDetails, Errors errors){

		 AjaxResponseBody result = new AjaxResponseBody();

		 //If error, just return a 400 bad request, along with the error message
		 if (errors.hasErrors()) {

			 result.setMsg(errors.getAllErrors()
					 .stream().map(x -> x.getDefaultMessage())
					 .collect(Collectors.joining(",")));

			 return ResponseEntity.badRequest().body(result);

		 }

		 System.out.println("****** contating Bengal Remedies *****"+contDetails.getEmail());
		 mainSend(contDetails);
		 result.setMsg(" Mail send successfully");
		 return ResponseEntity.ok(result);
	 }
	 
	 private void mainSend(ContactDetails toMail){
		  
		  try {
			smptp.sendSimpleMail(toMail );
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
}
