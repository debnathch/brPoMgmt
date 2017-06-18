package com.benRem.brPoMgmt.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity
@Table(name="br_purchase_order")
@Data
public class Purchase_Order implements Serializable {

	
    
	@Id
	@Column(name="po_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="order_from")
	private String orderFrom;
	
	@Column(name="order_date")
	private Date orderDate;

	@Column(name="po_details_id")
	private int  detailsId;
	

	
}
