package com.benRem.brPoMgmt.controller.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
/**
 * Created by debnathchatterjee on 10/06/17.
 */
public class GoogleLoginController {

    @RequestMapping(
            value = "user",
            method = RequestMethod.GET)
    public Principal user(Principal user) {
        return user;
    }
}
