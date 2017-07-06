package com.benRem.brPoMgmt.dao;

import java.math.BigInteger;

import com.benRem.brPoMgmt.domain.PurchaseOrder;
import com.benRem.brPoMgmt.repository.OrderCartRepository;
import com.benRem.brPoMgmt.reqResObj.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.benRem.brPoMgmt.repository.PurchaseOrderRepository;

@Component
public class PurchaeOrderDao {
	
	@Autowired
    private PurchaseOrderRepository purchaseOrderRepo;
	
	@Autowired
	private OrderCartRepository orderCartRepository;


	public String saveToCart(OrderItem orderCart){

		PurchaseOrder orderToCart = new PurchaseOrder();

		orderToCart.setCustomerId(BigInteger.valueOf(10));
		orderToCart.setPoId(BigInteger.valueOf(1));
		orderToCart.setIsActivate("Y");
		orderToCart.setIsCart("Y");
//
//		orderToCart.setProduct_id(Integer.parseInt(orderCart.getProd_id()));
//		orderToCart.setProduct_quantity(Integer.parseInt(orderCart.getProd_qty()));
//		orderToCart.setCustomer_id(BigInteger.valueOf(new Long("9836711699")));
		orderCartRepository.save(orderToCart);
		return "success";

	}

	public Iterable<PurchaseOrder> findFromCart(){

		return null;
	}

	public Iterable<PurchaseOrder> FindPurchaseOrder(){
		
		return purchaseOrderRepo.findAll();
	}


	private void saveCartWithOrderLine(){


	}


}
