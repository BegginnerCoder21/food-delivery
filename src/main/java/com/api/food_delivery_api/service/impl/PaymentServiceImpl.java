package com.api.food_delivery_api.service.impl;

import com.api.food_delivery_api.constante.Constant;
import com.api.food_delivery_api.dto.PaymentRequest;
import com.api.food_delivery_api.entity.Payment;
import com.api.food_delivery_api.enumeration.PaymentStatus;
import com.api.food_delivery_api.repository.PaymentRepository;
import com.api.food_delivery_api.service.PaymentService;
import com.api.food_delivery_api.service.handler.payments.BankHandlerPayment;
import com.api.food_delivery_api.service.handler.payments.CardHandlerPayment;
import com.api.food_delivery_api.service.handler.payments.CashHandlerPayment;
import com.api.food_delivery_api.utils.PaymentUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private BankHandlerPayment bankHandlerPayment;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CardHandlerPayment cardHandlerPayment;
    @Autowired
    private CashHandlerPayment cashHandlerPayment;

    @Override
    public PaymentStatus pay(PaymentRequest paymentRequest) {

        String payMethod = paymentRequest.getPaymentMethod().toString();
        if(PaymentUtils.BANK.equalsIgnoreCase(payMethod))
        {
            log.info("payment par virement bancaire. ");
            String response = this.bankHandlerPayment.bankProcessingPayment(paymentRequest);
            this.saveProcessingPayment(paymentRequest, response);

            if(StringUtils.hasText(response))
            {
                return PaymentStatus.SUCCESS;
            }

            return PaymentStatus.FAILED;
        }

        if(PaymentUtils.CARD.equalsIgnoreCase(payMethod))
        {
            log.info("payment par carte de credit. ");
            String response = this.cardHandlerPayment.cardProcessingPayment(paymentRequest);
            this.saveProcessingPayment(paymentRequest, response);

            if(StringUtils.hasText(response))
            {
                return PaymentStatus.SUCCESS;
            }

            return PaymentStatus.FAILED;
        }

        if(PaymentUtils.CASH.equalsIgnoreCase(payMethod))
        {
            log.info("payment cash cash. ");
            String response = this.cashHandlerPayment.cashProcessingPayment(paymentRequest);
            this.saveProcessingPayment(paymentRequest, response);

            if(StringUtils.hasText(response))
            {
                return PaymentStatus.SUCCESS;
            }

            return PaymentStatus.FAILED;
        }

        return PaymentStatus.FAILED;
    }

    @Override
    public String inquiry(String orderId) {
        return "";
    }

    public void saveProcessingPayment(PaymentRequest paymentRequest, String response)
    {
        Payment payment = new Payment();

        payment.setPaymentMethod(paymentRequest.getPaymentMethod());
        payment.setAmount(paymentRequest.getAmount());
        payment.setCreatedAt(new Date());
        payment.setCreatedBy(Constant.SYSTEM);
        payment.setPaymentStatus(PaymentStatus.FAILED);

        if(StringUtils.hasText(response))
        {
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
        }

        this.paymentRepository.save(payment);
    }
}
