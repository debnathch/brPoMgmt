package com.benRem.brPoMgmt.dao;

import java.util.ArrayList;
import java.util.List;

import com.benRem.brPoMgmt.repository.ProductRepository;
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
  private ProductRepository repo;



  
 // @RequestMapping(method = RequestMethod.GET)
  public List<Br_Product> findItems() {
	  List<Br_Product> target = new ArrayList<>();
	  repo.findAll().forEach(target::add);

      System.out.println("**** fetching of products done ******" );
      return target;
  }
  
  //@RequestMapping(method = RequestMethod.POST)
  public Br_Product addItem(@RequestBody Br_Product item) {
	
    return repo.save(item);
  }
  
  //@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public Br_Product updateItem(@RequestBody Br_Product updatedItem, @PathVariable Integer id) {
   // updatedItem.se;
    return repo.save(updatedItem);
  }
  
 // @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deleteItem(@PathVariable Long id) {
    repo.delete(id);
  }
  
  
}