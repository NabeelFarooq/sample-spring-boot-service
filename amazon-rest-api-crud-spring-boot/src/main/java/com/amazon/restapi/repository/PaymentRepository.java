package com.amazon.restapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.amazon.restapi.entity.PaymentEntity;



@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity, Long>{

}
