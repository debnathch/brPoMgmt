package com.benRem.brPoMgmt.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by debnathchatterjee on 10/06/17.
 */

@Controller
@RequestMapping("/")
public class LoginController {
    @RequestMapping("/")
    public String index()  {
        return "forward:start.html";
    }
}
