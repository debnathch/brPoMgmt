package com.benRem.brPoMgmt.reqResObj.response;

import com.benRem.brPoMgmt.domain.Company;
import lombok.Data;

import java.math.BigInteger;

/**
 * Created by debnathchatterjee on 21/06/17.
 */

@Data
public class OrderInCart {

    int product_id;
    Company company;
    String product_name;
    String product_description;

    String product_pack_size;

    BigInteger productQty;
}
