package org.springplayground.samples.demoapp.integration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springplayground.samples.demoapp.domain.Order;

@Component
public class ShippingRestClient {

    private final RestTemplate restTemplate;
    private final String shippingUrl;

    public ShippingRestClient(RestTemplateBuilder restTemplateBuilder,
                              @Value("${integration.shipping.url}") String shippingUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.shippingUrl = shippingUrl;
    }

    public String ship(Order order) {
        try {
            ResponseEntity<String> shipResponseEntity = restTemplate.postForEntity(shippingUrl, order.getId(), String.class);
            return shipResponseEntity.getBody();
        } catch (HttpStatusCodeException e) {
            throw new ShippingFailedException("Shipping failed: " + e.getStatusText(), e);
        }
    }

}
