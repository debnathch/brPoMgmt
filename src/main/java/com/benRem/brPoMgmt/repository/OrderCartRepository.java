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
public interface OrderCartRepository extends CrudRepository<PurchaseOrder, BigInteger> {

    //PurchaseOrder findOne(String filter, Map<String, Object> params);

   // `purchase_order`.`br_purchase_order`

    @Query("select o from PurchaseOrder o where  o.isCart=:isCart and o.isActivate=:isActivate and  o.customerId =:id")
    List<PurchaseOrder> findPurchaseOrderCartDetails(@Param("isCart") String isCart, @Param("isActivate") String isActivate, @Param("id") BigInteger id);

    @Query("select o from PurchaseOrderLineItem o where  o.poId =:id")
    List<PurchaseOrderLineItem> findCartItemDetails(@Param("id") BigInteger id);


    /*@Query("select * from `purchase_order`.`br_purchase_order` o where  `is_cart`='Y'  ")
    PurchaseOrder findCartDetails();*/
}
