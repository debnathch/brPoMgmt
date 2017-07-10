package com.benRem.brPoMgmt.repository;

import com.benRem.brPoMgmt.domain.PurchaseOrder;
import com.benRem.brPoMgmt.domain.PurchaseOrderLineItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by debnathchatterjee on 20/06/17.
 */
@Repository
public interface OrderCartRepository extends CrudRepository<PurchaseOrder, Long> {

    //PurchaseOrder findOne(String filter, Map<String, Object> params);

   // `purchase_order`.`br_purchase_order`

    @Query("select o from PurchaseOrder o where  is_cart=Y and 'company_id'=id ")
    PurchaseOrder findCartDetails(@Param("Y")String Y,@Param("id")Long id);

    /*@Query("select * from `purchase_order`.`br_purchase_order` o where  `is_cart`='Y'  ")
    PurchaseOrder findCartDetails();*/
}
