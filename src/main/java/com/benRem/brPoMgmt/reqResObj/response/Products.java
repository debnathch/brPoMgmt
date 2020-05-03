package com.benRem.brPoMgmt.reqResObj.response;

import com.benRem.brPoMgmt.domain.Company;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by debnathchatterjee on 20/06/17.
 */
@Data
public class Products {

    int product_id;

    CompanyMake companyMake;

    ProductType productType;

    String product_name;

    String product_description;

    String product_pack_size;

    String product_qty;


}

