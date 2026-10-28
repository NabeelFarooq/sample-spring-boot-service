package com.amazon.restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.amazon.restapi.request.OrderRequest;
import com.amazon.restapi.response.OrderResponse;
import com.amazon.restapi.service.OrderService;

// alt. of @RestController = @Controller + @ResponseBody
@RestController
@RequestMapping("order/")
public class OrderController {
	@Autowired
	OrderService orderService;

	@PostMapping("placeorder")
	public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) throws Exception {
		System.out.println("OrderController.placeOrder():::::::::::: START");
		OrderResponse orderResponse = orderService.placeOrder(orderRequest);
		System.out.println("OrderController.placeOrder():::::::::::: END");
		return orderResponse;
	}

	@GetMapping("getorder")
	public OrderResponse getOrder(@RequestParam int orderId) {
		System.out.println("OrderController.getOrder():::::::::::: START");
		OrderResponse orderResponse = orderService.getOrder(orderId);
		System.out.println("OrderController.getOrder():::::::::::: END");
		return orderResponse;
	}

	// get all orders
	@GetMapping("getallorders")
	public List<OrderResponse> getAllOrders() {
		List<OrderResponse> ordersList = orderService.sendAllOrders();
		return ordersList;
	}

	// get all orders by payment Method
	@GetMapping("getByPaymentTypeAndQuantity")
	public List<OrderResponse> getByPaymentType(@RequestParam String paymentType,@RequestParam int quantity) {
		List<OrderResponse> ordersList = orderService.getOrdersByPaymentTypeAndQuantity(paymentType,quantity);
		return ordersList;
	}

	// delete
	@DeleteMapping("delete/{orderId}")
	public String deleteOrder(@PathVariable int orderId) {
		System.out.println("OrderController.deleteOrder()");
		return orderService.deleteOrder(orderId);
	}
	/*
	 * // update order details
	 * 
	 * @PatchMapping public OrderResponse updateOrderDetails(@RequestBody
	 * OrderRequest orderRequest) {
	 * 
	 * }
	 */
}
