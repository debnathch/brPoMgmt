package com.benRem.brPoMgmt.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.benRem.brPoMgmt.domain.Br_Product;
import com.benRem.brPoMgmt.domain.Purchase_Order;
import com.benRem.brPoMgmt.repository.ProductRepository;
import com.benRem.brPoMgmt.repository.PurchaseOrderRepository;

@Component
public class PurchaeOrderDao {
	
	@Autowired
    private PurchaseOrderRepository purchaseOrderRepo;
	
	@Autowired
    private ProductRepository productRepository;
	
	public Iterable<Purchase_Order> FindPurchaseOrder(){
		
		return purchaseOrderRepo.findAll();
	}
	
public Iterable<Br_Product> FindProductList(){
		
		return productRepository.findAll();
	}

}
