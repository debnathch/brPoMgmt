package com.benRem.brPoMgmt.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by debnathchatterjee on 06/07/17.
 */

@Entity
@Table(name="br_purchase_order_line")
@Data
public class PurchaseOrderLineItem implements Serializable {


    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "po_line_item_id")
    BigInteger poLineItemId;

    @Column(name="po_id")
    BigInteger poId;

    @Column(name="prod_id")
    BigInteger prodId;

    @Column(name="product_quantity")
    BigInteger productQty;

    @OneToOne
            @JoinColumn(name="prod_id",referencedColumnName = "prod_id",updatable = false,insertable = false)
    Br_Product productItem;



}
