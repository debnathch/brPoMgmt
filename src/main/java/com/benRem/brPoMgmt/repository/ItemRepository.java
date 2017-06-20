package com.benRem.brPoMgmt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.benRem.brPoMgmt.domain.Br_Product;

@Repository
public interface ItemRepository extends CrudRepository<Br_Product, Long>{
	//List<Br_Product> findAllPurchaseOrder();
	
}