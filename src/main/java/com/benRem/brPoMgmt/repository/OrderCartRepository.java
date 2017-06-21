package com.benRem.brPoMgmt.repository;

import com.benRem.brPoMgmt.domain.Br_Product;
import com.benRem.brPoMgmt.domain.Order_Cart;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by debnathchatterjee on 20/06/17.
 */
public interface OrderCartRepository extends CrudRepository<Order_Cart, Long> {
}
