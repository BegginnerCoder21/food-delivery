package com.api.food_delivery_api.repository;

import com.api.food_delivery_api.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
