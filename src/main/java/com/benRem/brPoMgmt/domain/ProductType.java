package com.benRem.brPoMgmt.domain;

import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.util.List;

/**
 * Created by debnathchatterjee on 04/07/17.
 */

@Entity
@Table(name="br_product_type")
@Data
public class ProductType {

    @Id
    @Column(name="prod_type_id")
    int product_type_id;

    @Column(name="prod_type_name")
    String product_type_name;

    @Column(name="prod_type_drug_comb")
    String product_drug_type;

    @OneToMany(mappedBy = "br_product_type", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Br_Product> productList;


    public ProductType(){
    }

    public ProductType(String name){
        this.product_type_name = name;
    }

    public ProductType(String product_type_name,List<Br_Product> productList) {
        this.product_type_name = product_type_name;
        this.productList = productList;
    }

    public int getProduct_type_id() {
        return product_type_id;
    }

    public void setProduct_type_id(int product_type_id) {
        this.product_type_id = product_type_id;
    }

    public String getProduct_type_name() {
        return product_type_name;
    }

    public void setProduct_type_name(String product_type_name) {
        this.product_type_name = product_type_name;
    }

    public String getProduct_drug_type() {
        return product_drug_type;
    }

    public void setProduct_drug_type(String product_drug_type) {
        this.product_drug_type = product_drug_type;
    }

    public List<Br_Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Br_Product> productList) {
        this.productList = productList;
    }

    public String toString(){
        String info = "";
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("product_type_name",this.product_type_name);

        JSONArray productArray = new JSONArray();
        if(this.productList != null){
            this.productList.forEach(product->{
                JSONObject subJson = new JSONObject();
                subJson.put("product_name", product.getProduct_name());
                productArray.put(subJson);
            });
        }
        jsonInfo.put("products", productArray);
        info = jsonInfo.toString();
        return info;
    }
}
