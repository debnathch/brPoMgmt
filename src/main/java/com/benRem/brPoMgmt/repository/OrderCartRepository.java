package com.benRem.brPoMgmt.repository;

import com.benRem.brPoMgmt.domain.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by debnathchatterjee on 20/06/17.
 */
public interface OrderCartRepository extends CrudRepository<PurchaseOrder, Long> {
}
