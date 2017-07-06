package com.benRem.brPoMgmt.domain;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by debnathchatterjee on 20/06/17.
 */

@Entity
@Table(name="br_purchase_order")
@Data
public class PurchaseOrder implements Serializable {

    @Id
    @Column(name="po_id")
    BigInteger poId;


    @Column(name="customer_id")
    BigInteger customerId;


    @ManyToOne(optional = false)
            @JoinColumn(name="", referencedColumnName = "customer_id")
    private Customer customer;

    @Column(name="is_cart")
    private String isCart;

    @Column(name="is_active")
    private String isActivate;

}
