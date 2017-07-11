package com.benRem.brPoMgmt.controller.order;

import com.benRem.brPoMgmt.dao.PurchaeOrderDao;
import com.benRem.brPoMgmt.domain.CartProduct;
import com.benRem.brPoMgmt.reqResObj.OrderItem;
import com.benRem.brPoMgmt.reqResObj.response.AjaxResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by debnathchatterjee on 10/06/17.
 */

@Component
@RestController
@Slf4j

public class OrderController {
    @Autowired
    PurchaeOrderDao purchaseOrderDao;

    @RequestMapping(value ="/addCart", method = RequestMethod.POST)
    public ResponseEntity<?> addToCart(@Valid @RequestBody OrderItem orderItem)  {
        System.out.println(orderItem.getProd_id()+"****** entry to cart *****"+orderItem.getProd_qty());
        AjaxResponseBody result = new AjaxResponseBody();
        Integer customerId = Integer.parseInt("10");
        // add to cart to table
        if(purchaseOrderDao.saveToCart(customerId , orderItem).equalsIgnoreCase("success"))
            return ResponseEntity.ok(result) ;
        else
            return ResponseEntity.ok(HttpStatus.EXPECTATION_FAILED);

    }

    @RequestMapping(value ="/triggerToOrder", method = RequestMethod.POST)
    public ResponseEntity<?> triggerToOrder(@Valid @RequestBody String customerId)  {

        AjaxResponseBody result = new AjaxResponseBody();

        // add to cart to table
        if(purchaseOrderDao.triggerForOrder(customerId).equalsIgnoreCase("success"))
            return ResponseEntity.ok(result) ;
        else
            return ResponseEntity.ok(HttpStatus.EXPECTATION_FAILED);

    }

    @RequestMapping(value ="/fetchFromCart", method = RequestMethod.GET)
    public List<CartProduct> fetchFromCart() throws IOException {


        AjaxResponseBody result = new AjaxResponseBody();

        System.out.println("****** fetching from cart  *****");
        List<CartProduct> productList = new ArrayList<>();
        ObjectMapper objMapper = new ObjectMapper();


        for(CartProduct eachProduct : purchaseOrderDao.fetchCartDetails("10")) {

            productList.add(objMapper.readValue(eachProduct.toString(), CartProduct.class));
        }
        return  productList;

    }



}
