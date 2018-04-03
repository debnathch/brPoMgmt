package com.benRem.brPoMgmt.repository;

import com.benRem.brPoMgmt.domain.Company;
import com.benRem.brPoMgmt.domain.ProductType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.benRem.brPoMgmt.domain.Br_Product;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import java.math.BigInteger;
import java.util.List;


/*@NamedNativeQueries({

        @NamedNativeQuery(name = "Br_Product.deleteByCreatedTimeBetween",
                query = "DELETE FROM abc WHERE create_time BETWEEN ?1 AND ?2")

})*/

@Repository
public interface ProductRepository extends CrudRepository<Br_Product, Integer>{
	//List<Purchase_Order> findAllPurchaseOrder();

    @Transactional
    @Modifying
    @Query("delete from Br_Product as brProduct where brProduct.company = :company_id and brProduct.productType = :prod_type_id")
    Integer deleteProductForBulkUpload(@Param("company_id") Company company_id, @Param("prod_type_id") ProductType prod_type_id);

    @Query("select o from Br_Product o where o.productType = :prod_type_id and o.company = :company_id")
    List<Br_Product> findConditionalProductList(@Param("prod_type_id") ProductType prod_type_id, @Param("company_id") Company company_id);

}
