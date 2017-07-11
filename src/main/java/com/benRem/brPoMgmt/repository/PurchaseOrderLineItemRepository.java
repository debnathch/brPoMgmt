package com.benRem.brPoMgmt.repository;

import com.benRem.brPoMgmt.domain.PurchaseOrder;
import com.benRem.brPoMgmt.domain.PurchaseOrderLineItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by debnathchatterjee on 08/07/17.
 */
@Repository
public interface PurchaseOrderLineItemRepository extends CrudRepository<PurchaseOrderLineItem, BigInteger> {


    @Query("select o from PurchaseOrderLineItem o where  o.poId =:id")
    List<PurchaseOrderLineItem> findCartItemDetails(@Param("id") BigInteger id);

    @Transactional
    @Modifying
    @Query("UPDATE PurchaseOrderLineItem c SET c.productQty = :qty WHERE c.id = :id")
    Integer updatePOLineItemQty(@Param("qty") BigInteger qty, @Param("id") BigInteger id);
}
