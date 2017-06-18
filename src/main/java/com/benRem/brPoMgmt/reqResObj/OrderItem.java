package com.benRem.brPoMgmt.reqResObj;

import lombok.Data;

/**
 * Created by debnathchatterjee on 17/06/17.
 */

@Data
public class OrderItem {


    String prod_id;
    String prod_qty;

    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getProd_qty() {
        return prod_qty;
    }

    public void setProd_qty(String prod_qty) {
        this.prod_qty = prod_qty;
    }
}
