package com.amazon.restapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "amazon-order-details")
public class OrderEntity {
// req
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	 private int id;
	 private int orderId;
	 private int customerId;
	 private int productId;
	 private int quantity;
	 private String shippingAddress;
	 private String paymentMethod;
	 private double totalAmount;
	 private String orderStatus;
	 private String estimatedDelivery;
	 public int getId() {
		 return id;
	 }
	 public void setId(int id) {
		 this.id = id;
	 }
	 public int getOrderId() {
		 return orderId;
	 }
	 public void setOrderId(int orderId) {
		 this.orderId = orderId;
	 }
	 public int getCustomerId() {
		 return customerId;
	 }
	 public void setCustomerId(int customerId) {
		 this.customerId = customerId;
	 }
	 public int getProductId() {
		 return productId;
	 }
	 public void setProductId(int productId) {
		 this.productId = productId;
	 }
	 public int getQuantity() {
		 return quantity;
	 }
	 public void setQuantity(int quantity) {
		 this.quantity = quantity;
	 }
	 public String getShippingAddress() {
		 return shippingAddress;
	 }
	 public void setShippingAddress(String shippingAddress) {
		 this.shippingAddress = shippingAddress;
	 }
	 public String getPaymentMethod() {
		 return paymentMethod;
	 }
	 public void setPaymentMethod(String paymentMethod) {
		 this.paymentMethod = paymentMethod;
	 }
	 public double getTotalAmount() {
		 return totalAmount;
	 }
	 public void setTotalAmount(double totalAmount) {
		 this.totalAmount = totalAmount;
	 }
	 public String getOrderStatus() {
		 return orderStatus;
	 }
	 public void setOrderStatus(String orderStatus) {
		 this.orderStatus = orderStatus;
	 }
	 public String getEstimatedDelivery() {
		 return estimatedDelivery;
	 }
	 public void setEstimatedDelivery(String estimatedDelivery) {
		 this.estimatedDelivery = estimatedDelivery;
	 }
	 
}
