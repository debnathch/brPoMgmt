package com.benRem.brPoMgmt.repository;

import com.benRem.brPoMgmt.domain.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by debnathchatterjee on 20/06/17.
 */
@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {
    //List<Br_Product> findAllPurchaseOrder();

}
