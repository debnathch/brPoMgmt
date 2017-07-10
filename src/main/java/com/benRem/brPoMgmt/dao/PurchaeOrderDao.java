package com.benRem.brPoMgmt.dao;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import com.benRem.brPoMgmt.domain.PurchaseOrder;
import com.benRem.brPoMgmt.domain.PurchaseOrderLineItem;
import com.benRem.brPoMgmt.repository.OrderCartRepository;
import com.benRem.brPoMgmt.repository.PurchaseOrderLineItemRepository;
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

	@Autowired
	private PurchaseOrderLineItemRepository orderLineItemRepository;






	public String saveToCart(OrderItem orderCart){
		PurchaseOrder orderToCart = new PurchaseOrder();
		try {


			orderToCart.setCustomerId(BigInteger.valueOf(10));
			//orderToCart.setPoId(BigInteger.valueOf(1));
			orderToCart.setIsActivate("Y");
			orderToCart.setIsCart("Y");
			orderToCart.setOrderDate(new java.sql.Timestamp(System.currentTimeMillis()));
			orderToCart = orderCartRepository.save(orderToCart);

			if(orderToCart !=null) {

				PurchaseOrderLineItem purchaseOrderLineItem = new PurchaseOrderLineItem();

				purchaseOrderLineItem.setPoId(orderToCart.getPoId());

				purchaseOrderLineItem.setProdId(new BigInteger(orderCart.getProd_id()));
				purchaseOrderLineItem.setProductQty(new BigInteger(orderCart.getProd_qty()));


				orderLineItemRepository.save(purchaseOrderLineItem);

				findFromCart ("10");

			}
			else
				return "fail";
			return "success";

		} catch(Exception e) {
			if(orderToCart!=null && orderToCart.getPoId()!=null ){

				orderCartRepository.delete(orderToCart);
			}

			return "fail";

		}


	}

	//TODO
	public String triggerForOrder(OrderItem orderCart){


		return "success";

	}


	public Iterable<PurchaseOrder> findFromCart(String customerId){

		PurchaseOrder poCart = orderCartRepository.findCartDetails();

		System.out.println(" CART CART ********* "+ poCart.getPoId());

		List<PurchaseOrderLineItem> poLineItems = poCart.getPoLineItems();

		poLineItems.forEach(poLineItem -> System.out.println( "deb *************** " + poLineItem.getProductItem()));



		return null;
	}

	public Iterable<PurchaseOrder> FindPurchaseOrder(){
		
		return purchaseOrderRepo.findAll();
	}


	private void saveCartWithOrderLine(){


	}


}
