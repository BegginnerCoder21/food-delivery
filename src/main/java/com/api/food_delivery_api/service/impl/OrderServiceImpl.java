package com.api.food_delivery_api.service.impl;

import com.api.food_delivery_api.dto.OrderItemRequest;
import com.api.food_delivery_api.dto.OrderRequest;
import com.api.food_delivery_api.dto.OrderResponse;
import com.api.food_delivery_api.entity.*;
import com.api.food_delivery_api.enumeration.DeliveryStatus;
import com.api.food_delivery_api.enumeration.PaymentStatus;
import com.api.food_delivery_api.exeption.BadRequestErrorException;
import com.api.food_delivery_api.exeption.InternalServerErrorException;
import com.api.food_delivery_api.exeption.UserNotFoundErrorException;
import com.api.food_delivery_api.repository.*;
import com.api.food_delivery_api.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private DeliveryPartnerRepository deliveryPartnerRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    @Transactional
    public OrderResponse create(OrderRequest orderRequest) {

        boolean userExist = this.userRepository.existsById(orderRequest.getUserId());

        if(!userExist)
        {
            log.warn("Aucun utilisateur trouvé pour l'id {}", orderRequest.getUserId());
            throw new UserNotFoundErrorException("Utilisateur non trouvé!");
        }

        boolean restaurantExist = this.restaurantRepository.existsById(orderRequest.getRestaurantId());

        if(!restaurantExist)
        {
            log.error("Aucun restaurant trouvé pour l'id {}", orderRequest.getRestaurantId());
            throw new InternalServerErrorException("Restaurant non trouvé");
        }

        List<DeliveryPartner> deliveryPartnerAvailable = this.deliveryPartnerRepository.findAllByAvailable(true);

        if(deliveryPartnerAvailable.isEmpty())
        {
            log.warn("Aucun partenaire de livraison n'ai disponible");
            throw new InternalServerErrorException("Aucun partenaire de livraison n'ai disponible");
        }

        if(orderRequest.getOrderItemRequests().isEmpty())
        {
            log.warn("bad request error");
            throw new BadRequestErrorException("Requête mal envoyé");
        }

        Restaurant foundRestaurant = this.restaurantRepository.findById(orderRequest.getRestaurantId()).get();
        User foundUser = this.userRepository.findById(orderRequest.getUserId()).get();
        var assignDeliveryPartner = deliveryPartnerAvailable.get(0);

        Payment payment = Payment.builder()
                .paymentMethod(orderRequest.getPaymentRequest().getPaymentMethod())
                .description(orderRequest.getPaymentRequest().getPaymentDescription())
                .amount(orderRequest.getPaymentRequest().getAmount())
                .paymentStatus(PaymentStatus.PENDING)
                .build();

        payment.setCreatedAt(new Date());
        payment.setCreatedBy(foundUser.getUsername());

        log.info("Enregistrement des infos lié au payment de la commande");
        this.paymentRepository.save(payment);

        if(payment.getId() == null)
        {
            log.error("Payment de votre commande a echoué, veuillez reesayer plutard");
            throw new InternalServerErrorException("Payment de votre commande a echoué, veuillez reesayer plutard");
        }


        Order order = Order.builder()
                .orderDate(new Date())
                .deliveryFee(orderRequest.getDeliveryFee())
                .orderStatus(orderRequest.getOrderStatus())
                .tax(orderRequest.getTax())
                .deliveryPartnerId(assignDeliveryPartner.getId())
                .restaurantId(foundRestaurant.getId())
                .totalAmount(orderRequest.getTotalAmount())
                .orderId(orderRequest.getUserPhoneNumber() + UUID.randomUUID())
                .paymentId(payment.getId())
                .userId(foundUser.getId())
                .build();

        Delivery delivery = Delivery.builder()
                .orderId(order.getId())
                .deliveryStatus(DeliveryStatus.PENDING)
                .deliveryPartnerId(assignDeliveryPartner.getId())
                .deliveryAddress(foundUser.getAddress())
                .deliveryFee(orderRequest.getDeliveryFee())
                .pickupAddess(foundRestaurant.getLocation())
                .pickupTime(null)
                .build();

        log.info("Enregistrement de livraison");
        this.deliveryRepository.save(delivery);

        return null;
    }
}
