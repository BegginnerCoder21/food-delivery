package com.api.food_delivery_api.service.handler.payments;


import com.api.food_delivery_api.dto.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class BankHandlerPayment {

    @Autowired
    private RestTemplate restTemplate;

    public String bankProcessingPayment(PaymentRequest paymentRequest){

        try {
            String url = "https://bank-payment.example.com";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            headers.set("Authorization", "Bearer " + "my-private-token");

            HttpEntity httpEntity = new HttpEntity(paymentRequest, headers);

            log.info("demarrage du processus de payment par virement bancaire");
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);

            log.info("fin du processus de virement {}. ", response.getBody());

            if(response.getStatusCode().is2xxSuccessful())
            {
                return response.getBody();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
