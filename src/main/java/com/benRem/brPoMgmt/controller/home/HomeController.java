package com.benRem.brPoMgmt.controller.home;

import com.benRem.brPoMgmt.dao.CustomerDao;
import com.benRem.brPoMgmt.reqResObj.ContactDetails;
import com.benRem.brPoMgmt.reqResObj.ContactDetailsForOrder;
import com.benRem.brPoMgmt.reqResObj.response.AjaxResponseBody;
import com.benRem.brPoMgmt.services.mailService.SmptMailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println("****** landed to home page*****");
        return new RedirectView("index.html");
    }

    @RequestMapping(value ="/home", method = RequestMethod.GET)
    public RedirectView greeting()  {
        System.out.println("****** entry to website *****");

        return new RedirectView("start.html");
    }

    @RequestMapping(value ="/customerDetailsForProductOrder", method = RequestMethod.POST)
    public RedirectView customerDetailsForProductOrder(@Valid @RequestBody ContactDetailsForOrder contactDetailsForOrder){


       /* if(custDao.createCustomer(contactDetailsForOrder)) {
            System.out.println("****** contating Bengal Remedies *****"+contactDetailsForOrder.getContactEmail());
        }
*/


        return new RedirectView("productShop/indexShop.html");
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
