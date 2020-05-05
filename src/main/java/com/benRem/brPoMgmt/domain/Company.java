package com.benRem.brPoMgmt.domain;

import lombok.Data;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by debnathchatterjee on 19/06/17.
 */

@Entity
@Table(name="company", schema = "bengalremedi_app")
@Data
public class Company implements Serializable {

    @Id
    @Column(name="company_id")
    int company_id;

    @Column(name="company_name")
    String company_name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Br_Product> productList;


    @Column(name="parent_company_id")
    int parent_company_id;

    @Column(name="parent_company_name")
    String parent_company_name;

    @Column(name="company_address")
    String company_address;

    @Column(name="company_desc")
    String company_desc;

    public Company(){
    }

    public Company(String name){
        this.company_name = name;
    }

    public Company(String name, List<Br_Product> products){
        this.company_name = name;
        this.productList = products;
    }




    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public List<Br_Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Br_Product> productList) {
        this.productList = productList;
    }

    public int getParent_company_id() {
        return parent_company_id;
    }

    public void setParent_company_id(int parent_company_id) {
        this.parent_company_id = parent_company_id;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_desc() {
        return company_desc;
    }

    public void setCompany_desc(String company_desc) {
        this.company_desc = company_desc;
    }

    public String toString(){
        String info = "";
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("company_name",this.company_name);
        jsonInfo.put("parent_company_name",this.parent_company_name);

        JSONArray productArray = new JSONArray();
        if(this.productList != null){
            this.productList.forEach(product->{
                JSONObject subJson = new JSONObject();
                subJson.put("product_name", product.getProduct_name());
                productArray.put(subJson);
            });
        }
        //jsonInfo.put("products", productArray);
        info = jsonInfo.toString();
        return info;
    }
}
