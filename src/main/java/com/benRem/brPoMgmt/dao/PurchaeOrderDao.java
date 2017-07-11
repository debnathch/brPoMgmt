package com.benRem.brPoMgmt.dao;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.benRem.brPoMgmt.domain.Br_Product;
import com.benRem.brPoMgmt.domain.Customer;
import com.benRem.brPoMgmt.domain.PurchaseOrder;
import com.benRem.brPoMgmt.domain.PurchaseOrderLineItem;
import com.benRem.brPoMgmt.repository.CustomerRepository;
import com.benRem.brPoMgmt.repository.OrderCartRepository;
import com.benRem.brPoMgmt.repository.PurchaseOrderLineItemRepository;
import com.benRem.brPoMgmt.reqResObj.OrderItem;
import com.benRem.brPoMgmt.reqResObj.response.ProductLine;
import com.benRem.brPoMgmt.reqResObj.response.Products;
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

	@Autowired
	private CustomerRepository customerRepository;






	public String saveToCart(Integer customerId , OrderItem orderCart) {


		try {
			Customer cust = customerRepository.findOne(BigInteger.valueOf(customerId));
			List<PurchaseOrder> poCart = findPurchaseOrderAsCart(customerId.toString());

			if (poCart.isEmpty()) {
				PurchaseOrder orderToCart = new PurchaseOrder();
				orderToCart.setCustomerId(BigInteger.valueOf(customerId));

				orderToCart.setIsActivate("Y");
				orderToCart.setIsCart("Y");
				orderToCart.setOrderDate(new java.sql.Timestamp(System.currentTimeMillis()));
				orderToCart.setCustomer(cust);
				orderToCart = orderCartRepository.save(orderToCart);

				if (orderToCart != null) {

					PurchaseOrderLineItem purchaseOrderLineItem = new PurchaseOrderLineItem();

					purchaseOrderLineItem.setPoId(orderToCart.getPoId());

					purchaseOrderLineItem.setProdId(new BigInteger(orderCart.getProd_id()));
					purchaseOrderLineItem.setProductQty(new BigInteger(orderCart.getProd_qty()));


					orderLineItemRepository.save(purchaseOrderLineItem);
					return "success";
				/*	List<PurchaseOrderLineItem> cartItemList = orderToCart.getPoLineItems();
					if (cartItemList != null) {
						cartItemList = new ArrayList<PurchaseOrderLineItem>();
						cartItemList.add(purchaseOrderLineItem);
					}
					orderToCart.setPoLineItems(cartItemList);
					if (orderCartRepository.save(orderToCart) != null) {
						return "success";
					} else {
						return "fail";
					}*/

				} else
					return "fail";
			} else {
				PurchaseOrder orderToCart = poCart.get(0);

				PurchaseOrderLineItem purchaseOrderLineItem = new PurchaseOrderLineItem();

				purchaseOrderLineItem.setPoId(orderToCart.getPoId());

				purchaseOrderLineItem.setProdId(new BigInteger(orderCart.getProd_id()));
				purchaseOrderLineItem.setProductQty(new BigInteger(orderCart.getProd_qty()));


				orderLineItemRepository.save(purchaseOrderLineItem);
				return "success";
			}


		} catch (Exception e) {
			return "fail";

		}

	}

	//TODO
	public String triggerForOrder(String customerId){


		try {
			Customer cust = customerRepository.findOne(new BigInteger(customerId));
			List<PurchaseOrder> poCart = findPurchaseOrderAsCart(customerId.toString());

			/*if (!poCart.isEmpty()) {

				PurchaseOrder po = poCart.get(0);
				po.setOrderDate(new java.sql.Timestamp(System.currentTimeMillis()));
				po.setIsCart("N");
				orderCartRepository.save(po);

					PurchaseOrderLineItem purchaseOrderLineItem = poCart.get(0).getPoLineItems();

					purchaseOrderLineItem.setPoId(orderToCart.getPoId());

					purchaseOrderLineItem.setProdId(new BigInteger(orderCart.getProd_id()));
					purchaseOrderLineItem.setProductQty(new BigInteger(orderCart.getProd_qty()));


					orderLineItemRepository.save(purchaseOrderLineItem); }
*/
			return "success";


		} catch (Exception e) {
			return "fail";

		}

	}


	private List<PurchaseOrder> findPurchaseOrderAsCart(String customerId) {

		List<PurchaseOrder> poCart = new ArrayList<>();
		try{
			poCart = orderCartRepository.findPurchaseOrderCartDetails("Y","Y",new BigInteger(customerId));

		} catch(Exception e) {
			e.printStackTrace();
		}

		return poCart;
	}

	public List<ProductLine> fetchCartDetails(String customerId){

		List<ProductLine> productLines = new ArrayList<>();
		//List<Br_Product> orderItemsInCart = new ArrayList<>();
		try{

			List<PurchaseOrderLineItem> poLineItems = orderCartRepository.findCartItemDetails(findPurchaseOrderAsCart(customerId).get(0).getPoId());

			//poLineItems.forEach(poLineItem -> orderItemsInCart.add(poLineItem.getProductItem()));

			for(PurchaseOrderLineItem eachProduct : poLineItems){
				ProductLine productLine = new ProductLine();
				productLine.setBrProduct(eachProduct.getProductItem());
				productLine.setProductQty(eachProduct.getProductQty());
				productLines.add(productLine);
			}


		} catch(Exception e) {
			e.printStackTrace();
		}
		return productLines;
	}

	public Iterable<PurchaseOrder> FindPurchaseOrder(){
		
		return purchaseOrderRepo.findAll();
	}


	private void saveCartWithOrderLine(){


	}


}
