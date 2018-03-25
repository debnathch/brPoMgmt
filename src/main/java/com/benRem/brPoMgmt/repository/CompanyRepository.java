package com.benRem.brPoMgmt.repository;

import com.benRem.brPoMgmt.domain.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by debnathchatterjee on 20/06/17.
 */
@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {
    //List<Br_Product> findAllPurchaseOrder();

    @Query("select comp from Company comp where  comp.company_name = :company_name ")
    Company findCompanyIdByName(@Param("company_name") String company_name);

}
