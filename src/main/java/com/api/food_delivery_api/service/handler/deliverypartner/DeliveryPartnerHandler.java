package com.api.food_delivery_api.service.handler.deliverypartner;

import com.api.food_delivery_api.dto.DeliveryPartnerRequest;
import com.api.food_delivery_api.entity.DeliveryPartner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class DeliveryPartnerHandler {

    @Autowired
    private ModelMapper modelMapper;

    public DeliveryPartner convertDeliveryPartnerRequestToDeliverPartner(DeliveryPartnerRequest deliveryPartnerRequest)
    {
       return this.modelMapper.map(deliveryPartnerRequest, DeliveryPartner.class);
    }


}
