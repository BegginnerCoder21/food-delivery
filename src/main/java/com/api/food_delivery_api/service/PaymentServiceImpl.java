package com.api.food_delivery_api.service;

import com.api.food_delivery_api.dto.PaymentRequest;
import com.api.food_delivery_api.entity.Payment;
import com.api.food_delivery_api.enumeration.PaymentStatus;
import com.api.food_delivery_api.repository.PaymentRepository;
import com.api.food_delivery_api.service.handler.BankHandlerPayment;
import com.api.food_delivery_api.service.handler.CardHandlerPayment;
import com.api.food_delivery_api.service.handler.CashHandlerPayment;
import com.api.food_delivery_api.utils.PaymentUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Date;

@Slf4j
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private BankHandlerPayment bankHandlerPayment;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CardHandlerPayment cardHandlerPayment;
    @Autowired
    private CashHandlerPayment cashHandlerPayment;

    @Override
    public String pay(PaymentRequest paymentRequest) {

        String payMethod = paymentRequest.getPaymentMethod().toString();
        if(PaymentUtils.BANK.equalsIgnoreCase(payMethod))
        {
            log.info("payment par virement bancaire. ");
            String response = this.bankHandlerPayment.bankProcessingPayment(paymentRequest);
            this.saveProcessingPayment(paymentRequest, response);

            if(StringUtils.hasText(response))
            {
                return PaymentStatus.SUCCESS.toString();
            }

            return PaymentStatus.FAILED.toString();
        }

        if(PaymentUtils.CARD.equalsIgnoreCase(payMethod))
        {
            log.info("payment par carte de credit. ");
            String response = this.cardHandlerPayment.cardProcessingPayment(paymentRequest);
            this.saveProcessingPayment(paymentRequest, response);

            if(StringUtils.hasText(response))
            {
                return PaymentStatus.SUCCESS.toString();
            }

            return PaymentStatus.FAILED.toString();
        }

        if(PaymentUtils.CASH.equalsIgnoreCase(payMethod))
        {
            log.info("payment cash cash. ");
            String response = this.cashHandlerPayment.cashProcessingPayment(paymentRequest);
            this.saveProcessingPayment(paymentRequest, response);

            if(StringUtils.hasText(response))
            {
                return PaymentStatus.SUCCESS.toString();
            }

            return PaymentStatus.FAILED.toString();
        }

        return "";
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
        payment.setOrderId(paymentRequest.getOrderId());
        payment.setCreatedAt(new Date());
        payment.setCreatedBy("SYSTEM");
        payment.setPaymentStatus(PaymentStatus.FAILED);

        if(StringUtils.hasText(response))
        {
            payment.setPaymentStatus(PaymentStatus.SUCCESS);
        }

        this.paymentRepository.save(payment);
    }
}
