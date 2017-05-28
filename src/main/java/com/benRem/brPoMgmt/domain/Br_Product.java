package com.benRem.brPoMgmt.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="br_product_list")
@Data
public class Br_Product implements Serializable {
	
	@Id
	@Column(name="prod_id")
	int productId;
	
	@Column(name="prod_name")
	String product_name;
	
	@Column(name="prod_description")
	String product_description;
	
	@Column(name="prod_pack_size")
	String product_pack_size;
	
	@Column(name="prod_trade_price")
	Float product_trade_price;
	
	@Column(name="prod_mrp_incl_vat")
	Float product_mrp_price_icl_vat;
	
	@Column(name="prod_net_exclude_vat")
	Float product_net_exclude_vat;

	
	
	

}
