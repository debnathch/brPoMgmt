package com.benRem.brPoMgmt.repository;

import com.benRem.brPoMgmt.domain.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, BigInteger>{
	//List<Purchase_Order> findAllPurchaseOrder();


}

