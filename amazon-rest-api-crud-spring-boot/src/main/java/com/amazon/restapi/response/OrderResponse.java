package com.amazon.restapi.response;

public class OrderResponse {
		  private int orderId;
		  private int customerId;
		  private double totalAmount;
		  private String orderStatus;
		  private String estimatedDelivery;
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
