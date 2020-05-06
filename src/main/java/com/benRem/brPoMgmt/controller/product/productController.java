package com.benRem.brPoMgmt.controller.product;

import com.benRem.brPoMgmt.dao.ProductDao;
import com.benRem.brPoMgmt.domain.Br_Product;
import com.benRem.brPoMgmt.domain.Company;
import com.benRem.brPoMgmt.reqResObj.response.ProductType;
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
        log.debug("****** landed to shoping page*****");
        return new RedirectView("productShop/indexShop.html");
    }

    @RequestMapping(value="/product", method = RequestMethod.GET)
    public List<Products> orderPage() throws IOException {

        log.debug("****** Landing product page *****");
        List<Products> productList = new ArrayList<>();
        ObjectMapper objMapper = new ObjectMapper();
        for(Br_Product eachProduct : productDao.findItems()) {

            productList.add(objMapper.readValue(eachProduct.toString(), Products.class));
        }

        return  productList;
    }

    @RequestMapping(value = "/productType", method=RequestMethod.GET )
    public List<ProductType> getProductTypes() throws IOException {

        log.debug(" **** fetching the product Type ");
        List<ProductType> productTypeList = new ArrayList<>();

        ObjectMapper objMapper = new ObjectMapper();

        for(com.benRem.brPoMgmt.domain.ProductType eachProductType : productDao.findAllProductTypes()){

            productTypeList.add(objMapper.readValue(eachProductType.toString(), ProductType.class));
        }
        return  productTypeList;
    }

    @RequestMapping(value = "/companyType", method=RequestMethod.GET )
    public List<Company> getCompanyTypes() throws IOException {

        log.debug(" **** fetching the company Type ");
        List<Company> companyTypeList = new ArrayList<>();

        ObjectMapper objMapper = new ObjectMapper();

        for(com.benRem.brPoMgmt.domain.Company company : productDao.findCompanyTypes()){

            companyTypeList.add(objMapper.readValue(company.toString(), Company.class));
        }
        return  companyTypeList;
    }



}
