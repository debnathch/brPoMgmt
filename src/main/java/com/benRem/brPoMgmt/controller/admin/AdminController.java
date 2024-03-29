package com.benRem.brPoMgmt.controller.admin;

import com.benRem.brPoMgmt.dao.ProductDao;
import com.benRem.brPoMgmt.domain.Br_Product;
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
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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
import java.util.List;
import java.util.Map;

/**
 * Created by debnathchatterjee on 24/03/18.
 */

@Component
@RestController
@Slf4j

public class AdminController {


    @Autowired
    ProductDao productDao;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;

    @RequestMapping(value = "/prodfileupload", method = RequestMethod.POST)
    @ApiOperation("Upload XLSX file ")
    ResponseEntity<?> processExcelSheet(@ModelAttribute UploadProuctForm form) {

        AjaxResponseBody response = new AjaxResponseBody();
        try {
            log.debug(form.getCompany() + " " + form.getProdType());

            Company company = companyRepository.findCompanyIdByName(form.getCompany());
            ProductType prodType = productTypeRepository.findProdTypeIdByType(form.getProdType());
            boolean isContinue = false;

            List<Br_Product> currentProductList = productDao.findConditionalProductList(prodType, company);

            if(currentProductList!=null && currentProductList.size()>0){
                int succssDel = productDao.deleteItem(form.getCompany(), form.getProdType());


                if( succssDel == currentProductList.size()) {
                    isContinue = true;
                }
            } else {

                isContinue = true;


            }


            if (isContinue) {

                if (form.getProductExcel()[0].getName().contains(".xls") || form.getProductExcel()[0].getOriginalFilename().contains(".XLS")) {
                    InputStream stream = form.getProductExcel()[0].getInputStream();
                    HSSFWorkbook workbook = new HSSFWorkbook(stream);
                    HSSFSheet sheet = workbook.getSheetAt(0);

                    Iterator<Row> rowIterator = sheet.iterator();
                    int productCount = 0;
                    while (rowIterator.hasNext()) {
                        // Skip read heading
                        Row currentRow = rowIterator.next();
                        Iterator<Cell> cellIterator = currentRow.iterator();

                        if (productCount > 0) {

                            Br_Product brProduct = new Br_Product();

                            brProduct.setCompany(company);
                            brProduct.setProductType(prodType);
                            int column_count = 0;
                            while (cellIterator.hasNext()) {

                                Cell currentCell = cellIterator.next();
                                //getCellTypeEnum shown as deprecated for version 3.15
                                //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                                if (currentCell.getCellTypeEnum() == CellType.STRING) {
                                    log.debug(currentCell.getStringCellValue() + "--");


                                    if(column_count ==2)
                                        brProduct.setProduct_name(currentCell.getStringCellValue());
                                    if(column_count == 3)
                                        brProduct.setProduct_description(currentCell.getStringCellValue());
                                    if(column_count == 4)
                                        brProduct.setProduct_pack_size(currentCell.getStringCellValue());
                                   /* if(column_count == 5)
                                        brProduct.setProduct_trade_price();*/

                                } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {

                                    if(column_count == 1)
                                        brProduct.setProduct_hsn_code(Double.toString(currentCell.getNumericCellValue()));
                                }


                                column_count ++;
                            }

                            productDao.addItem(brProduct);
                        }
                        productCount ++;

                    }
                } else if (form.getProductExcel()[0].getName().contains(".xlsx") || form.getProductExcel()[0].getName().contains(".XLSX")) {

                    InputStream stream = form.getProductExcel()[0].getInputStream();
                    XSSFWorkbook workbook = new XSSFWorkbook(stream);
                    XSSFSheet sheet = workbook.getSheetAt(0);


                    Iterator<Row> rowIterator = sheet.iterator();
                    int productCount = 0;
                    while (rowIterator.hasNext()) {
                        // Skip read heading
                        Row currentRow = rowIterator.next();

                        Iterator<Cell> cellIterator = currentRow.iterator();

                        if (productCount > 0) {


                            while (cellIterator.hasNext()) {

                                Cell currentCell = cellIterator.next();
                                //getCellTypeEnum shown as deprecated for version 3.15
                                //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                                if (currentCell.getCellTypeEnum() == CellType.STRING) {
                                    log.debug(currentCell.getStringCellValue() + "--");
                                } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                                    log.debug(currentCell.getNumericCellValue() + "--");
                                }
                                log.debug("/n");


                            }

                        }
                        productCount++;
                    }
                }

                //productRepository.save();
                response.setMsg("The file has been successfully processed");

            }


            return ResponseEntity.ok(response);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok(HttpStatus.EXPECTATION_FAILED);
        }

    }

}
