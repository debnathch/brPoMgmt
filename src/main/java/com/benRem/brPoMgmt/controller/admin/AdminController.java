package com.benRem.brPoMgmt.controller.admin;

import com.benRem.brPoMgmt.domain.Company;
import com.benRem.brPoMgmt.domain.ProductType;
import com.benRem.brPoMgmt.repository.CompanyRepository;
import com.benRem.brPoMgmt.repository.ProductRepository;
import com.benRem.brPoMgmt.repository.ProductTypeRepository;
import com.benRem.brPoMgmt.repository.PurchaseOrderLineItemRepository;
import com.benRem.brPoMgmt.reqResObj.UploadProuctForm;
import com.benRem.brPoMgmt.reqResObj.response.AjaxResponseBody;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by debnathchatterjee on 24/03/18.
 */

@Component
@RestController
@Slf4j

public class AdminController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @RequestMapping(value = "/prodfileupload", method = RequestMethod.POST)
    @ApiOperation("Upload XLSX file ")
    ResponseEntity<?> processExcelSheet(@ModelAttribute UploadProuctForm form) {

        AjaxResponseBody response = new AjaxResponseBody();
        try {
            log.debug(form.getCompany() + " "+ form.getProdType());
            InputStream stream = form.getProductExcel()[0].getInputStream();
            /*XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();*/
            Company comp = companyRepository.findCompanyIdByName(form.getCompany());
            ProductType prodType = productTypeRepository.findProdTypeIdByType(form.getProdType());
           int isdeleted = productRepository.deleteProductForBulkUpload(comp, prodType);
            System.out.println(isdeleted);


            /*while (rowIterator.hasNext()) {
                // Skip read heading

            }*/
            //productRepository.save();
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok(HttpStatus.EXPECTATION_FAILED);
        }

    }

}
