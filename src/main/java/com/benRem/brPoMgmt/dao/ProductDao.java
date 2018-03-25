package com.benRem.brPoMgmt.dao;

import java.util.ArrayList;
import java.util.List;

import com.benRem.brPoMgmt.domain.Company;
import com.benRem.brPoMgmt.domain.ProductType;
import com.benRem.brPoMgmt.repository.CompanyRepository;
import com.benRem.brPoMgmt.repository.ProductRepository;
import com.benRem.brPoMgmt.repository.ProductTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.benRem.brPoMgmt.domain.Br_Product;


@Component
@RestController
@Slf4j
@RequestMapping("/items")
public class ProductDao {
  @Autowired
  private ProductRepository ProductRepo;
  @Autowired
  private ProductTypeRepository productTypeRepo;
  @Autowired
  private CompanyRepository companyRepository;




  
 // @RequestMapping(method = RequestMethod.GET)
  public List<Br_Product> findItems() {
	  List<Br_Product> target = new ArrayList<>();
      ProductRepo.findAll().forEach(target::add);

      System.out.println("**** fetching of products done ******" );
      return target;
  }

  public List<ProductType> findAllProductTypes() {
      List<ProductType> productTypes = new ArrayList<>();
      productTypeRepo.findAll().forEach(productTypes :: add);
      return  productTypes;
  }

    public List<Company> findCompanyTypes() {
        List<Company> companyTypes = new ArrayList<>();
        companyRepository.findAll().forEach(companyTypes :: add);
        return  companyTypes;
    }
  
  //@RequestMapping(method = RequestMethod.POST)
  public Br_Product addItem(@RequestBody Br_Product item) {
	
    return ProductRepo.save(item);
  }
  
  //@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public Br_Product updateItem(@RequestBody Br_Product updatedItem, @PathVariable Integer id) {
   // updatedItem.se;
    return ProductRepo.save(updatedItem);
  }
  
 // @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public int deleteItem(String company, String productType) {
      Company comp = companyRepository.findCompanyIdByName(company);
      ProductType prodType = productTypeRepo.findProdTypeIdByType(productType);
      return ProductRepo.deleteProductForBulkUpload(comp, prodType);

  }
  
  
}