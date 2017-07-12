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
@Table(name="customer", schema = "purchase_order")
@Data
public class Customer implements Serializable {

    @Id
    @Column(name = "customer_id")
    BigInteger customerId;

    @Column(name="customer_name")
    String customerName;

    @Column(name="customer_GST")
    String custGstNum;

    @Column(name="customer_lisc_num")
    String custLscNum;

    @Column(name="customer_email")
    String custEmail;

    @Column(name="customer_Phone1")
    String custPrimeryPhone;

    @Column(name="customer_Phone2")
    String custSecondaryPhone;

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

    public String getCustGstNum() {
        return custGstNum;
    }

    public void setCustGstNum(String custGstNum) {
        this.custGstNum = custGstNum;
    }

    public String getCustLscNum() {
        return custLscNum;
    }

    public void setCustLscNum(String custLscNum) {
        this.custLscNum = custLscNum;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustPrimeryPhone() {
        return custPrimeryPhone;
    }

    public void setCustPrimeryPhone(String custPrimeryPhone) {
        this.custPrimeryPhone = custPrimeryPhone;
    }

    public String getCustSecondaryPhone() {
        return custSecondaryPhone;
    }

    public void setCustSecondaryPhone(String custSecondaryPhone) {
        this.custSecondaryPhone = custSecondaryPhone;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }





}
