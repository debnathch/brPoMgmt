package com.benRem.brPoMgmt.controller.home;

import com.benRem.brPoMgmt.dao.CustomerDao;
import com.benRem.brPoMgmt.domain.Customer;
import com.benRem.brPoMgmt.reqResObj.ContactDetails;
import com.benRem.brPoMgmt.reqResObj.ContactDetailsForOrder;
import com.benRem.brPoMgmt.reqResObj.response.AjaxResponseBody;
import com.benRem.brPoMgmt.services.mailService.SmptMailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * Created by debnathchatterjee on 10/06/17.
 */

@Component
@RestController
@Slf4j

public class HomeController {


    @Autowired
    SmptMailSender smptp;
    @Autowired
    CustomerDao custDao;

    @RequestMapping(value ="/welcome", method = RequestMethod.GET)
    public RedirectView welcome()  {
        log.debug("****** landed to home page*****");
        return new RedirectView("index.html");
    }

    @RequestMapping(value ="/home", method = RequestMethod.GET)
    public String greeting()  {
        log.debug("****** entry to website *****");

        return "forward:start.html";
    }



    @RequestMapping(value ="/customerDetailsForProductOrder", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> customerDetailsForProductOrder(@Valid @RequestBody ContactDetailsForOrder contactDetailsForOrder){

        log.debug(contactDetailsForOrder.getContactEmail()+"****** entry to add product *****"+contactDetailsForOrder.getContactPhone());

        Customer returnCustomer = null;
        try {
            returnCustomer = custDao.searchForCustomer(contactDetailsForOrder.getContactPhone());
            if(returnCustomer ==null){
                returnCustomer = custDao.createCustomer(contactDetailsForOrder);

            }
            return ResponseEntity.ok(returnCustomer) ;
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(HttpStatus.EXPECTATION_FAILED);
        }







    }


    @RequestMapping(value ="/contactUs", method = RequestMethod.POST, consumes = "application/json")

    public ResponseEntity<?> contactUs(@Valid @RequestBody ContactDetails contDetails){

        AjaxResponseBody result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message

        log.debug("****** contating Bengal Remedies *****"+contDetails.getEmail());
        try {
            mainSend(contDetails);
            result.setMsg(" Mail send successfully");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            result.setMsg(" Issue with Mail send.. Please contact with us later");
            e.printStackTrace();
            return ResponseEntity.ok(HttpStatus.EXPECTATION_FAILED);

        }

    }




    private void mainSend(ContactDetails toMail) throws Exception{


            smptp.sendSimpleMail(toMail );

    }


}
