package com.benRem.brPoMgmt.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

import org.json.JSONObject;


@Entity
@Table(name="br_product_list", schema = "heroku_7f5f4c0dfe6e1e3")
@Data
public class Br_Product implements Serializable {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="prod_id")
	int product_id;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id")
	private Company company;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prod_type_id")
	private ProductType productType;


	@Column(name="prod_name")
	String product_name;
	
	@Column(name="prod_description")
	String product_description;

	@Column(name="prod_pack_size")
	String product_pack_size;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prod_cat_type_code")
	private ProductType productTypeCat;

	@Column(name="prod_hsn_code")
	String product_hsn_code;


	/*@Column(name="prod_mrp_incl_vat")
	Float product_mrp_price_icl_vat;
	
	@Column(name="prod_net_exclude_vat")
	Float product_net_exclude_vat;*/


	public Br_Product(){
	}

	public Br_Product(String name){
		this.product_name = name;
	}

	public Br_Product(String name, Company company,ProductType productType){
		this.product_name = name;
		this.company = company;
		this.productType = productType;
	}

	public ProductType getProductTypeCat() {
		return productTypeCat;
	}

	public void setProductTypeCat(ProductType productTypeCat) {
		this.productTypeCat = productTypeCat;
	}

	public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public String getProduct_pack_size() {
		return product_pack_size;
	}

	public void setProduct_pack_size(String product_pack_size) {
		this.product_pack_size = product_pack_size;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_hsn_code() {
		return product_hsn_code;
	}

	public void setProduct_hsn_code(String product_hsn_code) {
		this.product_hsn_code = product_hsn_code;
	}

	public String toString(){
		String info = "";

		JSONObject jsonInfo = new JSONObject();
		jsonInfo.put("product_id",this.product_id);
		jsonInfo.put("product_name",this.product_name);

		JSONObject companyObj = new JSONObject();
		companyObj.put("company_name", this.company.getCompany_name());
		companyObj.put("company_id", this.company.getCompany_id());
		companyObj.put("parent_company_name", this.company.getParent_company_name());

		jsonInfo.put("companyMake", companyObj);
		JSONObject productType = new JSONObject();
		productType.put("prod_type_name", this.productType.getProd_type_name());
		productType.put("product_drug_type", this.productType.getProduct_drug_type());
		jsonInfo.put("productType",productType);
		jsonInfo.put("product_description",this.product_description);
		jsonInfo.put("product_pack_size",this.product_pack_size);
		jsonInfo.put("product_url",this.product_hsn_code);
		jsonInfo.put("prod_cat_type_code",this.productTypeCat.getProd_type_name());
		info = jsonInfo.toString();
		return info;
	}
}
