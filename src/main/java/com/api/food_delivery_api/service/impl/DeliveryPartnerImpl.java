package com.api.food_delivery_api.service.impl;

import com.api.food_delivery_api.dto.DeliveryPartnerRequest;
import com.api.food_delivery_api.dto.DeliveryPartnerResponse;
import com.api.food_delivery_api.entity.DeliveryPartner;
import com.api.food_delivery_api.repository.DeliveryPartnerRepository;
import com.api.food_delivery_api.service.DeliveryPartnerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryPartnerImpl implements DeliveryPartnerService {

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DeliveryPartnerResponse create(DeliveryPartnerRequest deliveryPartnerRequest) {

        DeliveryPartner deliveryPartner = this.modelMapper.map(deliveryPartnerRequest, DeliveryPartner.class);
        this.deliveryPartnerRepository.save(deliveryPartner);

        return this.modelMapper.map(deliveryPartner, DeliveryPartnerResponse.class);
    }

    @Override
    public DeliveryPartnerResponse update(Long id, DeliveryPartnerRequest deliveryPartnerRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<DeliveryPartnerResponse> getAll() {
        return List.of();
    }

    @Override
    public DeliveryPartnerResponse getById(Long id) {
        return null;
    }
}
