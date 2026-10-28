package com.amazon.restapi.request;

public class OrderRequest {
	 private int customerId;
	 private int productId;
	 private int quantity;
	 private String shippingAddress;
	 private String paymentMethod;
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
	 
}
