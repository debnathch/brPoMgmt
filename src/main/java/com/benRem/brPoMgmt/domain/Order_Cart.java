package com.benRem.brPoMgmt.domain;

import com.sun.javafx.beans.IDProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by debnathchatterjee on 20/06/17.
 */

@Entity
@Table(name="order_cart")
@Data
public class Order_Cart {

    @Id
    @Column(name="customer_id")
    BigInteger customer_id;


    @Column(name="prod_id")
    int product_id;

    @Column(name="product_quantity")
    int product_quantity;

}
