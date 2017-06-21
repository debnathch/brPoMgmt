package com.benRem.brPoMgmt.dao;

import java.math.BigInteger;
import java.util.List;

import com.benRem.brPoMgmt.domain.Order_Cart;
import com.benRem.brPoMgmt.repository.OrderCartRepository;
import com.benRem.brPoMgmt.reqResObj.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.benRem.brPoMgmt.domain.Br_Product;
import com.benRem.brPoMgmt.domain.Purchase_Order;
import com.benRem.brPoMgmt.repository.ProductRepository;
import com.benRem.brPoMgmt.repository.PurchaseOrderRepository;

@Component
public class PurchaeOrderDao {
	
	@Autowired
    private PurchaseOrderRepository purchaseOrderRepo;
	
	@Autowired
	private OrderCartRepository orderCartRepository;


	public String saveToCart(OrderItem orderCart){

		Order_Cart orderToCart = new Order_Cart();
		orderToCart.setProduct_id(Integer.parseInt(orderCart.getProd_id()));
		orderToCart.setProduct_quantity(Integer.parseInt(orderCart.getProd_qty()));
		orderToCart.setCustomer_id(BigInteger.valueOf(new Long("9836711699")));
		orderCartRepository.save(orderToCart);
		return "success";

	}


	public Iterable<Purchase_Order> FindPurchaseOrder(){
		
		return purchaseOrderRepo.findAll();
	}


	public void saveToCart(){


	}


}
