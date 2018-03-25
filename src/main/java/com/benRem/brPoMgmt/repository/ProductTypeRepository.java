package com.benRem.brPoMgmt.repository;

import com.benRem.brPoMgmt.domain.ProductType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by debnathchatterjee on 20/02/18.
 */
@Repository
public interface ProductTypeRepository extends CrudRepository<ProductType, Integer>{

    @Query("select prodType from ProductType prodType where  prodType.prod_type_name = :prod_type_name ")
    ProductType findProdTypeIdByType(@Param("prod_type_name") String prod_type_name);
}
