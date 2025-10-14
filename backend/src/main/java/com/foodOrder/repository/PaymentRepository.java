package com.foodOrder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodOrder.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByOrderOrderId(Long orderId);

    List<Payment> findByMenuItemItemId(Long itemId);
}
