package com.foodOrder.service;

import java.util.List;

import com.foodOrder.entity.Payment;

public interface PaymentService {

    Payment addPayment(Payment payment);

    Payment getPaymentById(Long paymentId);

    List<Payment> getAllPayments();

    void deletePayment(Long paymentId);
}
