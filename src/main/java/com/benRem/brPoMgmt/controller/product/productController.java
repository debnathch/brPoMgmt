package com.benRem.brPoMgmt.controller.product;

import com.benRem.brPoMgmt.dao.ProductDao;
import com.benRem.brPoMgmt.dao.PurchaeOrderDao;
import com.benRem.brPoMgmt.domain.Br_Product;
import com.benRem.brPoMgmt.reqResObj.response.Products;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by debnathchatterjee on 10/06/17.
 */
@Component
@RestController
@Slf4j


public class productController {


    @Autowired
    ProductDao productDao;


    @RequestMapping(value ="/productShop", method = RequestMethod.GET)
    public RedirectView productGallery()  {
        System.out.println("****** landed to shoping page*****");
        return new RedirectView("productShop/indexShop.html");
    }

    @RequestMapping(value="/product", method = RequestMethod.GET)
    public List<Products> orderPage() throws IOException {

        System.out.println("****** Landing product page *****");
        List<Products> productList = new ArrayList<>();
        ObjectMapper objMapper = new ObjectMapper();
        for(Br_Product eachProduct : productDao.findItems()) {

            productList.add(objMapper.readValue(eachProduct.toString(), Products.class));
        }

        return  productList;


    }

}
