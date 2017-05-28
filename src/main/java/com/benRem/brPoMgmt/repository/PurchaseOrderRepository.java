package com.benRem.brPoMgmt.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.benRem.brPoMgmt.domain.Purchase_Order;

@Repository
public interface PurchaseOrderRepository extends CrudRepository<Purchase_Order, Long>{
	//List<Purchase_Order> findAllPurchaseOrder();
	
}

