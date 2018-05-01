package com.benRem.brPoMgmt.domain;

import lombok.Data;
import org.json.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by debnathchatterjee on 06/07/17.
 */

@Entity
@Table(name="customer", schema = "heroku_b623f1513b9ff48")
@Data
public class Customer implements Serializable {

    //@GeneratedValue(strategy=GenerationType.AUTO)
    @Id
    @Column(name = "customer_id")
    BigInteger customerId;

    @Column(name="customer_name")
    String customerName;

    @Column(name="customer_gst")
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

    public String toString(){
        String info = "";

        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("customerId",this.customerId);
        jsonInfo.put("customerName",this.customerName);
        jsonInfo.put("custGstNum",this.custGstNum);
        jsonInfo.put("custLscNum",this.custLscNum);
        jsonInfo.put("custEmail",this.custEmail);
        jsonInfo.put("custPrimeryPhone",this.custPrimeryPhone);
        jsonInfo.put("custSecondaryPhone",this.custSecondaryPhone);
        jsonInfo.put("customerAddress",this.customerAddress);
        jsonInfo.put("customerType",this.customerType);

        info = jsonInfo.toString();
        return info;
    }



}
