package com.benRem.brPoMgmt.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by debnathchatterjee on 06/07/17.
 */

@Entity
@Table(name="customer")
@Data
public class Customer implements Serializable {

    @Id
    @Column(name = "customer_id")
    BigInteger customerId;

    @Column(name="customer_name")
    String customerName;


    @Column(name="customer_address")
    String customerAddress;

    @Column(name="customer_type")
    String customerType;


    public BigInteger getCustomerId() {
        return customerId;
    }

    public void setCustomerId(BigInteger customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }





}
