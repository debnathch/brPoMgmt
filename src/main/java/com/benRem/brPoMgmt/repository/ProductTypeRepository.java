package com.benRem.brPoMgmt.repository;

import com.benRem.brPoMgmt.domain.ProductType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by debnathchatterjee on 20/02/18.
 */
@Repository
public interface ProductTypeRepository extends CrudRepository<ProductType, Long>{
}
