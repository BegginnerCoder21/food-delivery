package com.api.food_delivery_api.service.impl;

import com.api.food_delivery_api.dto.DeliveryPartnerRequest;
import com.api.food_delivery_api.dto.DeliveryPartnerResponse;
import com.api.food_delivery_api.entity.DeliveryPartner;
import com.api.food_delivery_api.repository.DeliveryPartnerRepository;
import com.api.food_delivery_api.service.DeliveryPartnerService;
import com.api.food_delivery_api.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DeliveryPartnerImpl implements DeliveryPartnerService {

    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DeliveryPartnerResponse create(DeliveryPartnerRequest deliveryPartnerRequest) {

        try {
            UserUtils.usernameNotEmpty(deliveryPartnerRequest.getUsername());
            UserUtils.phoneNumberNotEmpty(deliveryPartnerRequest.getPhoneNumber());

            DeliveryPartner deliveryPartner = this.modelMapper.map(deliveryPartnerRequest, DeliveryPartner.class);
            deliveryPartner.setCreatedAt(new Date());
            deliveryPartner.setCreatedBy("SYSTEM");

            this.deliveryPartnerRepository.save(deliveryPartner);
            log.info("creation du partenaire de livraison {}", deliveryPartner.getId());

            return this.modelMapper.map(deliveryPartner, DeliveryPartnerResponse.class);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    @Override
    public DeliveryPartnerResponse update(Long id, DeliveryPartnerRequest deliveryPartnerRequest) {

        boolean deliveryPartnerExist = this.deliveryPartnerRepository.existsById(id);

        if(!deliveryPartnerExist)
        {
            return DeliveryPartnerResponse.builder().build();
        }

        DeliveryPartner deliveryPartner = this.deliveryPartnerRepository.findById(id).get();

        this.modelMapper.map(deliveryPartnerRequest, deliveryPartner);
        deliveryPartner.setUpdatedAt(new Date());
        deliveryPartner.setUpdatedBy("SYSTEM");

        /*
        deliveryPartner.setAvailable(deliveryPartnerRequest.isAvailable());
        deliveryPartner.setGender(deliveryPartnerRequest.getGender());
        deliveryPartner.setEmail(deliveryPartnerRequest.getEmail());
        deliveryPartner.setAddress(deliveryPartnerRequest.getAddress());
        deliveryPartner.setDateOfBirth(deliveryPartnerRequest.getDateOfBirth());
        deliveryPartner.setFirstname(deliveryPartnerRequest.getFirstname());
        deliveryPartner.setLastname(deliveryPartnerRequest.getLastname());
        deliveryPartner.setUsername(deliveryPartner.getUsername());
        deliveryPartner.setVehicleType(deliveryPartnerRequest.getVehicleType());
        deliveryPartner.setPassword(deliveryPartner.getPassword());
        deliveryPartner.setPhoneNumber(deliveryPartnerRequest.getPhoneNumber());

         */

        this.deliveryPartnerRepository.save(deliveryPartner);

        return this.modelMapper.map(deliveryPartner, DeliveryPartnerResponse.class);
    }

    @Override
    public void delete(Long id) {

        boolean deliveryPartner = this.deliveryPartnerRepository.existsById(id);

        if (!deliveryPartner)
        {
            return;
        }

        this.deliveryPartnerRepository.deleteById(id);
    }

    @Override
    public List<DeliveryPartnerResponse> getAll() {

        List<DeliveryPartner> allDeliveryPartner = this.deliveryPartnerRepository.findAll();

        List<DeliveryPartnerResponse> deliveryPartnerList = new ArrayList<>();

        for (DeliveryPartner deliveryPartner : allDeliveryPartner){

            DeliveryPartnerResponse deliveryPartnerResponse = this.modelMapper.map(deliveryPartner, DeliveryPartnerResponse.class);
            deliveryPartnerList.add(deliveryPartnerResponse);
        }

        return deliveryPartnerList;
    }

    @Override
    public DeliveryPartnerResponse getById(Long id) {
        return null;
    }
}
