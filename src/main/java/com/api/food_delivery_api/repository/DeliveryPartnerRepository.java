package com.api.food_delivery_api.repository;

import com.api.food_delivery_api.entity.DeliveryPartner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryPartnerRepository extends JpaRepository<DeliveryPartner, Long> {
}
