package com.benRem.brPoMgmt.repository;

import com.benRem.brPoMgmt.domain.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long>{
	//List<Purchase_Order> findAllPurchaseOrder();
	
}

