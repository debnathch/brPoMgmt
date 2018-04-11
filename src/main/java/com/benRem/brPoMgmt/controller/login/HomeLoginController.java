package com.benRem.brPoMgmt.controller.login;

import lombok.extern.slf4j.Slf4j;
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

}
