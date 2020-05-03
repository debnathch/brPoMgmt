package com.benRem.brPoMgmt.controller.order;

import com.benRem.brPoMgmt.dao.PurchaeOrderDao;
import com.benRem.brPoMgmt.domain.PurchaseOrder;
import com.benRem.brPoMgmt.repository.PurchaseOrderRepository;
import com.benRem.brPoMgmt.reqResObj.response.CartProduct;
import com.benRem.brPoMgmt.reqResObj.OrderItem;
import com.benRem.brPoMgmt.reqResObj.response.AjaxResponseBody;
import com.benRem.brPoMgmt.services.mailService.SmptMailSender;
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
import java.math.BigInteger;
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

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepo;

    @Autowired
    SmptMailSender smptp;



    @RequestMapping(value ="/addCart", method = RequestMethod.POST)
    public ResponseEntity<?> addToCart(@Valid @RequestBody OrderItem orderItem)  {
        log.debug(orderItem.getProd_id()+"****** entry to cart *****"+orderItem.getProd_qty());
        AjaxResponseBody result = new AjaxResponseBody();
        BigInteger customerId = new BigInteger(orderItem.getCustomerId());
        // add to cart to table
        if(purchaseOrderDao.saveToCart(customerId , orderItem).equalsIgnoreCase("success"))
            return ResponseEntity.ok(result) ;
        else
            return ResponseEntity.ok(HttpStatus.EXPECTATION_FAILED);

    }

    @RequestMapping(value ="/delFromCart", method = RequestMethod.POST)
    public ResponseEntity<?> delFromCart(@Valid @RequestBody OrderItem orderItem)  {
        log.debug(orderItem.getProd_id()+"****** entry to cart *****"+orderItem.getProd_qty());
        AjaxResponseBody result = new AjaxResponseBody();
        BigInteger customerId = new BigInteger(orderItem.getCustomerId());
        // add to cart to table
        if(purchaseOrderDao.delFromCart(customerId , orderItem).equalsIgnoreCase("success"))
            return ResponseEntity.ok(result) ;
        else
            return ResponseEntity.ok(HttpStatus.EXPECTATION_FAILED);

    }

    @RequestMapping(value ="/triggerToOrder", method = RequestMethod.POST)
    public ResponseEntity<?> triggerToOrder( String customerId)  {

        AjaxResponseBody result = new AjaxResponseBody();
        log.debug(" ****** Placing your order ********** "+ customerId);
        // add to cart to table
        if(purchaseOrderDao.triggerForOrder(customerId).equalsIgnoreCase("success")) {

            return ResponseEntity.ok(result) ;
        }

        else
            return ResponseEntity.ok(HttpStatus.EXPECTATION_FAILED);

    }

    @RequestMapping(value ="/fetchFromCart", method = RequestMethod.GET)
    public List<CartProduct> fetchFromCart(String customerId) throws IOException {


        AjaxResponseBody result = new AjaxResponseBody();
        //CustomerId = "9836711699";
        log.debug("****** fetching from cart  *****");
        List<CartProduct> productList = new ArrayList<>();
        ObjectMapper objMapper = new ObjectMapper();


        for(CartProduct eachProduct : purchaseOrderDao.fetchCartDetails(customerId)) {

            productList.add(objMapper.readValue(eachProduct.toString(), CartProduct.class));
        }
        return  productList;

    }

    @RequestMapping(value ="/fetchCartCount", method = RequestMethod.GET)
    public String fetchAllFromCart(String customerId) throws IOException {

        int cartCount = 0;
        if(customerId !=null){
            List<PurchaseOrder> poList= purchaseOrderDao.findPurchaseOrderAsCart(customerId);

            if(poList !=null){
                cartCount = poList.size();
                for(PurchaseOrder po : poList){
                    // check any orderItem is there in the po or not. if there is no order item delete the po.
                    if(po.getPoLineItems().isEmpty()) {
                        purchaseOrderRepo.delete(po.getPoId());
                        cartCount --;
                    }
                }


                return  String.valueOf(cartCount);
            } else {
                return "0";
            }
        } else {
            return  "0";
        }
    }

    @RequestMapping(value ="/sendChartToCustomer", method = RequestMethod.GET)
    public ResponseEntity<?> sendRateList( String customerId) {
        AjaxResponseBody result = new AjaxResponseBody();

        if(purchaseOrderDao.triggertoSendRateChart(customerId).equalsIgnoreCase("success")) {
            result.setMsg("Rate chart is send to your given mail ID .. Please contact us with other company rate chart");
            return  ResponseEntity.ok(result);
        } else {
            result.setMsg("Issue faced to send mail");
            return  ResponseEntity.ok(HttpStatus.EXPECTATION_FAILED);
        }

    }


}
