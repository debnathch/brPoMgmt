package com.benRem.brPoMgmt.domain;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by debnathchatterjee on 20/06/17.
 */

@Entity
@Table(name="br_purchase_order")
@Data
public class PurchaseOrder implements Serializable {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="po_id")
    BigInteger poId;


    @Column(name="customer_id")
    BigInteger customerId;


    @ManyToOne(optional = false)
            @JoinColumn(name="customer_id", referencedColumnName = "customer_id", updatable = false,insertable = false)
    private Customer customer;

    @OneToMany
    @JoinColumn(name="po_id", referencedColumnName = "po_id")
    private List<PurchaseOrderLineItem> poLineItems;

    @Column(name="is_cart")
    private String isCart;

    @Column(name="is_active")
    private String isActivate;

    @Column(name="order_date")
    private Timestamp orderDate;

}
