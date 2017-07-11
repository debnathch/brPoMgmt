package com.benRem.brPoMgmt.domain;

import lombok.Data;
import org.json.JSONObject;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by 149970 on 7/11/2017.
 */
@Data
public class CartProduct {


    int product_id;

    private Company company;

    String product_name;

    String product_description;

    String product_pack_size;

    BigInteger product_qty;

    public String toString(){
        String info = "";

        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("product_id",this.product_id);
        jsonInfo.put("product_name",this.product_name);

        JSONObject companyObj = new JSONObject();
        companyObj.put("company_name", this.company.getCompany_name());
        companyObj.put("company_id", this.company.getCompany_id());
        jsonInfo.put("company", companyObj);
        jsonInfo.put("product_description",this.product_description);
        jsonInfo.put("product_pack_size",this.product_pack_size);
        jsonInfo.put("product_qty", this.product_qty);


        info = jsonInfo.toString();
        return info;
    }
}
