package com.benRem.brPoMgmt.reqResObj.response;

import lombok.Data;

import java.util.List;

/**
 * Created by debnathchatterjee on 20/06/17.
 */
@Data
public class CompanyMake {

    String company_id;
    String company_name;
    String parent_company_name;
    //List<Products> products;

}
