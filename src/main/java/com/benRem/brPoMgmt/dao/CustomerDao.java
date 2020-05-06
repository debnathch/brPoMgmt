package com.benRem.brPoMgmt.dao;

import com.benRem.brPoMgmt.domain.Customer;
import com.benRem.brPoMgmt.repository.CustomerRepository;
import com.benRem.brPoMgmt.reqResObj.ContactDetailsForOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Created by debnathchatterjee on 13/07/17.
 */
@Component
@RestController
@Slf4j
public class CustomerDao {

    @Autowired
    CustomerRepository customerRepository;

    public Customer createCustomer(ContactDetailsForOrder contactDetailsForOrder){

        Customer customer = new Customer();
        customer.setCustomerId(new BigInteger(contactDetailsForOrder.getContactPhone()));
        customer.setCustomerName(contactDetailsForOrder.getContactName());
        customer.setCustEmail(contactDetailsForOrder.getContactEmail());
        customer.setCustPrimeryPhone(contactDetailsForOrder.getContactPhone());
        return customerRepository.save(customer);

    }

    public Optional<Customer> searchForCustomer(String custId){

       return  customerRepository.findById(new BigInteger(custId));
    }

    public void updateCustomer(){

    }


}
