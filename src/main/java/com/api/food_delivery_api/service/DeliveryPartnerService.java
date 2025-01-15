package com.api.food_delivery_api.service;

import com.api.food_delivery_api.dto.DeliveryPartnerRequest;
import com.api.food_delivery_api.dto.DeliveryPartnerResponse;

import java.util.List;

public interface DeliveryPartnerService {

    DeliveryPartnerResponse create(DeliveryPartnerRequest deliveryPartnerRequest);
    DeliveryPartnerResponse update(Long id, DeliveryPartnerRequest deliveryPartnerRequest);
    void delete(Long id);
    List<DeliveryPartnerResponse> getAll();
    DeliveryPartnerResponse getById(Long id);
}
