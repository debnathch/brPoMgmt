package com.benRem.brPoMgmt.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by debnathchatterjee on 06/07/17.
 */

@Entity
@Table(name="br_purchase_order_line" , schema = "heroku_b623f1513b9ff48")
@Data
public class PurchaseOrderLineItem implements Serializable {


    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "po_line_item_id")
    BigInteger poLineItemId;

    @Column(name="po_id")
    BigInteger poId;

    @Column(name="prod_id")
    int prodId;

    @Column(name="prod_name")
    String product_name;

    @Column(name="prod_description")
    String product_description;

    @Column(name="prod_pack_size")
    String product_pack_size;

    @Column(name="prod_trade_price")
    Float product_trade_price;

    @Column(name="prod_hsn_code")
    String product_hsn_code;

    @Column(name="product_quantity")
    BigInteger productQty;

   /* @OneToOne
            @JoinColumn(name="prod_id",referencedColumnName = "prod_id",updatable = false,insertable = false)
    Br_Product productItem;*/



}
