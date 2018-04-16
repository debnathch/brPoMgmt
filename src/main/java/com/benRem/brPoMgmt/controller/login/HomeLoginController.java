package com.benRem.brPoMgmt.controller.login;

import com.benRem.brPoMgmt.reqResObj.response.AjaxResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by debnathchatterjee on 10/06/17.
 */

@Component
@RestController
@Slf4j

public class HomeLoginController {

    @RequestMapping(value ="/login", method = RequestMethod.GET)
    public RedirectView login()  {
        log.debug("****** entry to website *****");

        return new RedirectView("productShop/login.html");
    }


    @RequestMapping(value ="/adminLogin", method = RequestMethod.POST)
    public ResponseEntity<?> adminLogin(String password)  {

        AjaxResponseBody result = new AjaxResponseBody();

        // add to cart to table
        if(password.equalsIgnoreCase("ADMINBR")) {

            result.setMsg("success");
            return ResponseEntity.ok(result) ;
        } else
            return ResponseEntity.ok(HttpStatus.EXPECTATION_FAILED);

    }
}
