package com.benRem.brPoMgmt.reqResObj.response;

import com.benRem.brPoMgmt.domain.Br_Product;
import lombok.Data;

import java.math.BigInteger;

/**
 * Created by debnathchatterjee on 11/07/17.
 */
@Data
public class ProductLine {
    Br_Product brProduct;
    BigInteger productQty;
}
