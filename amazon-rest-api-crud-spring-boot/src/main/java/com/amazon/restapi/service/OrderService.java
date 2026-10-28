package com.amazon.restapi.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.amazon.restapi.entity.OrderEntity;
import com.amazon.restapi.entity.PaymentEntity;
import com.amazon.restapi.exception.OrderNotFoundException;
import com.amazon.restapi.repository.OrderRepository;
import com.amazon.restapi.repository.PaymentRepository;
import com.amazon.restapi.request.OrderRequest;
import com.amazon.restapi.response.OrderResponse;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	PaymentRepository paymentRepository;
	
	@Transactional
	public OrderResponse placeOrder(OrderRequest orderRequest) throws Exception {
		System.out.println("OrderService.placeOrder():::::::::::: START");
		OrderEntity orderEntity = new OrderEntity();
		OrderResponse orderResponse = new OrderResponse();
		PaymentEntity paymentEntity = new PaymentEntity();
		orderEntity.setCustomerId(orderRequest.getCustomerId());
		orderEntity.setProductId(orderRequest.getProductId());
		orderEntity.setQuantity(orderRequest.getQuantity());
		orderEntity.setShippingAddress(orderRequest.getShippingAddress());
		orderEntity.setPaymentMethod(orderRequest.getPaymentMethod());
		orderEntity.setOrderId(generateOrderId());
		orderEntity.setTotalAmount(generateAmount(orderRequest.getQuantity()));
		orderEntity.setOrderStatus(generateOrderStatus());
		orderEntity.setEstimatedDelivery(generateDeliveryTime());

		OrderEntity responseEntity = orderRepository.save(orderEntity);
		
		paymentEntity.setAmount(generateAmount(orderRequest.getQuantity()));
		paymentEntity.setOrderId(responseEntity.getOrderId());
		paymentEntity.setPaymentMethod(orderRequest.getPaymentMethod());
		paymentEntity.setPaymentStatus("Success");
		
		
//		String str = null;
//		if(str.equals("Nabeel")) {
//			System.out.println("Hi");
//		}
		
		paymentEntity = paymentRepository.save(paymentEntity);
		if(paymentEntity.getPaymentId() > 0) {
			orderResponse.setCustomerId(responseEntity.getCustomerId());
			orderResponse.setEstimatedDelivery(responseEntity.getEstimatedDelivery());
			orderResponse.setOrderId(responseEntity.getOrderId());
			orderResponse.setOrderStatus(responseEntity.getOrderStatus());
			orderResponse.setTotalAmount(responseEntity.getTotalAmount());
		}
		else {
//			orderResponse.setCustomerId(responseEntity.getCustomerId());
//			orderResponse.setEstimatedDelivery(responseEntity.getEstimatedDelivery());
//			orderResponse.setOrderId(responseEntity.getOrderId());
//			orderResponse.setOrderStatus("on-Hold");
//			orderResponse.setTotalAmount(responseEntity.getTotalAmount());
			throw new Exception("Payment failed!");
		}
		System.out.println("OrderService.placeOrder():::::::::::: END");
		return orderResponse;

	}

	public OrderResponse getOrder(int orderId) {
		System.out.println("OrderService.getOrder():::::::::::: START");

		OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
		if(orderEntity == null) {
			throw new OrderNotFoundException("Order with orderId: "+orderId+" does not exist");
		}
		OrderResponse orderResponse = new OrderResponse();

		orderResponse.setCustomerId(orderEntity.getCustomerId());
		orderResponse.setEstimatedDelivery(orderEntity.getEstimatedDelivery());
		orderResponse.setOrderId(orderEntity.getOrderId());
		orderResponse.setOrderStatus(orderEntity.getOrderStatus());
		orderResponse.setTotalAmount(orderEntity.getTotalAmount());
		System.out.println("OrderService.getOrder():::::::::::: END");
		return orderResponse;
	}

	public List<OrderResponse> sendAllOrders() {
		System.out.println("OrderService.sendAllOrders():::::::::::: START");
		List<OrderEntity> ordersResponseEntity = (List<OrderEntity>) orderRepository.findAll();
		int noOfOrders = ordersResponseEntity.size();
		List<OrderResponse> ordersResponse = new ArrayList<OrderResponse>();
		OrderEntity orderEntity = new OrderEntity();

		for (int i = 0; i < noOfOrders; i++) {
			orderEntity = ordersResponseEntity.get(i);
			OrderResponse orderResponse = new OrderResponse();
			orderResponse.setCustomerId(orderEntity.getCustomerId());
			orderResponse.setEstimatedDelivery(orderEntity.getEstimatedDelivery());
			orderResponse.setOrderId(orderEntity.getOrderId());
			orderResponse.setOrderStatus(orderEntity.getOrderStatus());
			orderResponse.setTotalAmount(orderEntity.getTotalAmount());
			ordersResponse.add(orderResponse);
		}
		System.out.println("OrderService.sendAllOrders():::::::::::: END");
		return ordersResponse;
	}
	@Transactional
	public String deleteOrder(int orderId) {
		System.out.println("OrderService.deleteOrder():::::::::::: START");
		OrderEntity orderEntity = null;
		orderEntity = orderRepository.findByOrderId(orderId);
		if(orderEntity == null) {
			throw new OrderNotFoundException("Order with orderId: "+orderId+" does not exist");
		}

		orderRepository.deleteByOrderId(orderId);
		System.out.println("OrderService.deleteOrder():::::::::::: END");
		return "Order with order id:" + orderId + " deleted successfully";

	}

	public List<OrderResponse> getOrdersByPaymentTypeAndQuantity(String paymentType, int quantity) {
		List<OrderEntity> entityList = orderRepository.getOrderByPaymentMethodAndQuantity(paymentType,quantity);
		List<OrderResponse> responseList = new ArrayList<>();
		for(OrderEntity orderEntity : entityList) {
			OrderResponse orderResponse =  new OrderResponse();
			orderResponse.setCustomerId(orderEntity.getCustomerId());
			orderResponse.setEstimatedDelivery(orderEntity.getEstimatedDelivery());
			orderResponse.setOrderId(orderEntity.getOrderId());
			orderResponse.setOrderStatus(orderEntity.getOrderStatus());
			orderResponse.setTotalAmount(orderEntity.getTotalAmount());
			responseList.add(orderResponse);
		}
		return responseList;
	}
	
	private int generateOrderId() {
		return (int) (System.currentTimeMillis() % 1000000);
	}

	private double generateAmount(int qty) {
		Random random = new Random();
		double price = 10000 + random.nextDouble(90000);
		return price * qty;
	}

	private String generateOrderStatus() {
		boolean paymentSuccessful = true;

		if (paymentSuccessful) {
			return "CONFIRMED";
		}

		return "PAYMENT_FAILED";
	}

	private String generateDeliveryTime() {
		LocalDate deliveryDate = LocalDate.now().plusDays(3);

		return deliveryDate.toString();
	}
}
