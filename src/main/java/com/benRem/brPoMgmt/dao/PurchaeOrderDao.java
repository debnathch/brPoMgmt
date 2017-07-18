package com.benRem.brPoMgmt.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.benRem.brPoMgmt.domain.*;
import com.benRem.brPoMgmt.repository.CustomerRepository;
import com.benRem.brPoMgmt.repository.OrderCartRepository;
import com.benRem.brPoMgmt.repository.PurchaseOrderLineItemRepository;
import com.benRem.brPoMgmt.reqResObj.OrderItem;
import com.benRem.brPoMgmt.reqResObj.response.CartProduct;
import com.benRem.brPoMgmt.services.mailService.SmptMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.benRem.brPoMgmt.repository.PurchaseOrderRepository;
import util.puchaseOrderConstant;

@Component
public class PurchaeOrderDao {
	
	@Autowired
    private PurchaseOrderRepository purchaseOrderRepo;
	
	@Autowired
	private OrderCartRepository orderCartRepository;

	@Autowired
	private PurchaseOrderLineItemRepository orderLineItemRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	SmptMailSender smptp;




// this method is saving the cart item for each add of each item.

	public String saveToCart(Integer customerId , OrderItem orderCart) {


		try {
			Customer cust = customerRepository.findOne(BigInteger.valueOf(customerId));
			List<PurchaseOrder> poCart = findPurchaseOrderAsCart(customerId.toString());

			// customer dont have any product in cart
			if (poCart.isEmpty()) {
				PurchaseOrder orderToCart = new PurchaseOrder();
				orderToCart.setCustomerId(BigInteger.valueOf(customerId));

				orderToCart.setIsActivate(puchaseOrderConstant.isActivate.constParam());
				orderToCart.setIsCart(puchaseOrderConstant.isCart.constParam());
				orderToCart.setOrderDate(new java.sql.Timestamp(System.currentTimeMillis()));
				orderToCart.setCustomer(cust);
				orderToCart = orderCartRepository.save(orderToCart);

				// id cart is saved successfully then system will add the line item with this cart.
				if (orderToCart != null) {

					PurchaseOrderLineItem purchaseOrderLineItem = new PurchaseOrderLineItem();

					purchaseOrderLineItem.setPoId(orderToCart.getPoId());

					purchaseOrderLineItem.setProdId(new BigInteger(orderCart.getProd_id()));
					purchaseOrderLineItem.setProductQty(new BigInteger(orderCart.getProd_qty()));


					orderLineItemRepository.save(purchaseOrderLineItem);
					return "success";
				} else
					return "fail";
			} else {
				PurchaseOrder orderToCart = poCart.get(0);
				//TODO need to check whether this product is there in cart item or not. If there is any product , system will update the quantity.
				boolean isUpdated = false;
				for(PurchaseOrderLineItem poLineItem : orderToCart.getPoLineItems()){

					if(new BigInteger(orderCart.getProd_id()).intValue()==(poLineItem.getProductItem().getProduct_id())){
						System.out.println("**** yahooooo item is in cart ******");
						isUpdated = true;
						updatePoLineItemWithQty(poLineItem, poLineItem.getProductQty().intValue()+Integer.parseInt(orderCart.getProd_qty()));
					}
				}
				// new line item is going to be inserted
				if(!isUpdated){
					PurchaseOrderLineItem purchaseOrderLineItem = new PurchaseOrderLineItem();

					purchaseOrderLineItem.setPoId(orderToCart.getPoId());

					purchaseOrderLineItem.setProdId(new BigInteger(orderCart.getProd_id()));
					purchaseOrderLineItem.setProductQty(new BigInteger(orderCart.getProd_qty()));


					orderLineItemRepository.save(purchaseOrderLineItem);
				}

				return "success";
			}


		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}

	}

	// this method will update the quantity of the added cart item.
	private void updatePoLineItemWithQty(PurchaseOrderLineItem poLineItem, int updatedQty) {
		System.out.println(updatedQty + "  ********* updated qty ");
		orderLineItemRepository.updatePOLineItemQty(new BigInteger(String.valueOf(updatedQty)), poLineItem.getPoLineItemId() );
	}

	//method will trigger for order
	public String triggerForOrder(String customerId){


		try {
			Customer cust = customerRepository.findOne(new BigInteger(customerId));
			List<PurchaseOrder> poCart = findPurchaseOrderAsCart(customerId.toString());

			if (!poCart.isEmpty()) {

				PurchaseOrder po = poCart.get(0);
				updateCartToOrder(po.getPoId());

				smptp.sendMailForOrder(cust, po.getPoLineItems());

				System.out.println(" Mail sent and order placed successfully ");
			}

			return "success";


		} catch (Exception e) {
			e.printStackTrace();
			return "fail";

		}

	}
	// this method will update the PO order from cart to order status
	private void updateCartToOrder(BigInteger poId) {

		orderCartRepository.updateFromCartToOrder(puchaseOrderConstant.isOrder.constParam(),  poId);
	}

	// this method will retrun the purchase order which have been added in cart
	public List<PurchaseOrder> findPurchaseOrderAsCart(String customerId) {

		List<PurchaseOrder> poCart = new ArrayList<>();
		try{
			poCart = orderCartRepository.findPurchaseOrderCartDetails(puchaseOrderConstant.isCart.constParam(),puchaseOrderConstant.isActivate.constParam(),new BigInteger(customerId));

		} catch(Exception e) {
			e.printStackTrace();
		}

		return poCart;
	}

	// this method will retrun the cart item details from cart
	public List<CartProduct> fetchCartDetails(String customerId){

		List<CartProduct> cartProducts = new ArrayList<>();
		try{

			if(!findPurchaseOrderAsCart(customerId).isEmpty()) {
				List<PurchaseOrderLineItem> poLineItems = orderLineItemRepository.findCartItemDetails(findPurchaseOrderAsCart(customerId).get(0).getPoId());

				//poLineItems.forEach(poLineItem -> orderItemsInCart.add(poLineItem.getProductItem()));

				for(PurchaseOrderLineItem poLine : poLineItems){

					CartProduct cartProduct = new CartProduct();
					Br_Product brProduct = poLine.getProductItem();
					cartProduct.setCompany(brProduct.getCompany());
					cartProduct.setProduct_description(brProduct.getProduct_description());
					cartProduct.setProduct_id(brProduct.getProduct_id());
					cartProduct.setProduct_name(brProduct.getProduct_name());
					cartProduct.setProduct_pack_size(brProduct.getProduct_pack_size());
					cartProduct.setProduct_qty(poLine.getProductQty().toString());

					cartProducts.add(cartProduct);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return cartProducts;
	}


	public Iterable<PurchaseOrder> FindPurchaseOrder(){
		
		return purchaseOrderRepo.findAll();
	}


	private void saveCartWithOrderLine(){


	}


}
