package com.foodOrder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
