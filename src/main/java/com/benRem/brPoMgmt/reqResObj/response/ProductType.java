package com.benRem.brPoMgmt.reqResObj.response;

import lombok.Data;

import java.util.List;

/**
 * Created by debnathchatterjee on 19/02/18.
 */
@Data
public class ProductType {

    String prod_type_id;
    String prod_type_name;
    String product_drug_type;
    List<Products> products;

}
