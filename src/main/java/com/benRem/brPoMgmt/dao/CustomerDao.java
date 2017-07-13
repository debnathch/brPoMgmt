package com.benRem.brPoMgmt.dao;

import com.benRem.brPoMgmt.domain.Customer;
import com.benRem.brPoMgmt.repository.CustomerRepository;
import com.benRem.brPoMgmt.reqResObj.ContactDetailsForOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by debnathchatterjee on 13/07/17.
 */
@Component
@RestController
@Slf4j
public class CustomerDao {

    @Autowired
    CustomerRepository customerRepository;

    public boolean createCustomer(ContactDetailsForOrder contactDetailsForOrder){

        Customer customer = new Customer();
        customer.setCustomerName(contactDetailsForOrder.getContactName());
        customer.setCustEmail(contactDetailsForOrder.getContactEmail());
        customer.setCustPrimeryPhone(contactDetailsForOrder.getContactPhone());
        if(customerRepository.save(customer)!=null){
            return true;
        } else {
            return false;
        }
    }

    public void searchForCustomer(){

    }

    public void updateCustomer(){

    }


}
