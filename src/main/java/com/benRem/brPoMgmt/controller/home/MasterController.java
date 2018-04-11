package com.benRem.brPoMgmt.controller.home;

import com.benRem.brPoMgmt.domain.Config;
import com.benRem.brPoMgmt.domain.Customer;
import com.benRem.brPoMgmt.reqResObj.ContactDetailsForOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Component
@RestController
@Slf4j


public class MasterController {

    @Value("${server.port}")
    private String port;

    @Value("${server.host}")
    private String host;

    @RequestMapping(value = "/loadConfig", method = RequestMethod.GET)
    public ResponseEntity<?> loadConfig() {

        log.debug("load config from spring YML");

        Config configParams = new Config();
        try {
            configParams.setHost(host);
            configParams.setPort(port);
            return ResponseEntity.ok(configParams);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(HttpStatus.EXPECTATION_FAILED);
        }


    }
}
