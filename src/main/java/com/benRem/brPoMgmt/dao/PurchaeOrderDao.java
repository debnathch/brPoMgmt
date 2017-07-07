package com.benRem.brPoMgmt.dao;

import java.math.BigInteger;

import com.benRem.brPoMgmt.domain.PurchaseOrder;
import com.benRem.brPoMgmt.domain.PurchaseOrderLineItem;
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

		PurchaseOrderLineItem purchaseOrderLineItem = new PurchaseOrderLineItem();

		purchaseOrderLineItem.setPoId(BigInteger.valueOf(1));

		purchaseOrderLineItem.setProdId(new BigInteger(orderCart.getProd_id()));
		purchaseOrderLineItem.setProductQty(new BigInteger(orderCart.getProd_qty()));

		if(orderCartRepository.save(orderToCart) !=null) {
			purchaseOrderRepo.save(orderToCart);

			for(PurchaseOrder po : orderCartRepository.findAll()){
				System.out.println(po.getPoLineItems().get(0).getPoLineItemId() + "$$$$$$$$$$$$  BAPI $$$$$$$$$$$$$$$$$$$");
			}
		}
		else
			return "fail";
		return "success";

	}

	//TODO
	public String triggerForOrder(OrderItem orderCart){

//		PurchaseOrder orderToCart = new PurchaseOrder();
//
//		orderToCart.setCustomerId(BigInteger.valueOf(10));
//		orderToCart.setPoId(BigInteger.valueOf(1));
//		orderToCart.setIsActivate("Y");
//		orderToCart.setIsCart("Y");
//
//		purchaseOrderLineItem.setPoId(BigInteger.valueOf(1));
//
//		purchaseOrderLineItem.setProdId(BigInteger.valueOf(Long.getLong(orderCart.getProd_id())));
//		purchaseOrderLineItem.setProductQty(BigInteger.valueOf(Long.getLong(orderCart.getProd_qty())));
//
//		orderCartRepository.save(orderToCart);
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
