package com.benRem.brPoMgmt.repository;

import com.benRem.brPoMgmt.domain.Company;
import com.benRem.brPoMgmt.domain.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

/**
 * Created by 149970 on 7/10/2017.
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, BigInteger> {
}
