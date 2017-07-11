package com.benRem.brPoMgmt.reqResObj.response;

import lombok.Data;

/**
 * Created by debnathchatterjee on 21/06/17.
 */

@Data
public class OrderInCart {

    Products products;
    int productQty;
}
