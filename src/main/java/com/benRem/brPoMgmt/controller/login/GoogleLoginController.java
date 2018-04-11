package com.benRem.brPoMgmt.controller.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
/**
 * Created by debnathchatterjee on 10/06/17.
 */
@Slf4j
public class GoogleLoginController {

    @RequestMapping(
            value = "user",
            method = RequestMethod.GET)
    public Principal user(Principal user) {
        if (user != null) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) user;
            Authentication authentication = oAuth2Authentication.getUserAuthentication();
            Map<String, String> details = new LinkedHashMap<>();
            details = (Map<String, String>) authentication.getDetails();
            log.info("details = " + details);  // id, email, name, link etc.

            details.get("email");


            return user;
        }
        return null;

    }
}
