package com.benRem.brPoMgmt.repository;

import com.benRem.brPoMgmt.domain.PurchaseOrder;
import com.benRem.brPoMgmt.domain.PurchaseOrderLineItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by debnathchatterjee on 08/07/17.
 */
@Repository
public interface PurchaseOrderLineItemRepository extends CrudRepository<PurchaseOrderLineItem, Long> {
    //List<Purchase_Order> findAllPurchaseOrder();

}
