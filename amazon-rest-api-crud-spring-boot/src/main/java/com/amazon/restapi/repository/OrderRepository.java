package com.amazon.restapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.amazon.restapi.entity.OrderEntity;



public interface OrderRepository extends CrudRepository<OrderEntity, Integer>{
	 OrderEntity findByOrderId(int orderId);
	 void deleteByOrderId(int orderId);
	 // sql 
	 @Query(value = "select * from `amazon-order-details` where payment_method = :paymentType and quantity > :quantity;", nativeQuery = true)
	 // jpql
	//@Query(value = "select o from OrderEntity o where o.paymentMethod = :paymentType and o.quantity > :quantity")
	List<OrderEntity> getOrderByPaymentMethodAndQuantity(String paymentType, int quantity);
}
