package org.springplayground.samples.loggingjsonapplication;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PingRestConsumer {

    private final RestTemplate restTemplate;

    public PingRestConsumer(final RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String ping() {
        try {
            restTemplate.getForEntity("https://www.vg.no", String.class);
            return "UP!";
        } catch (final Exception e) {
            return "DOWN!";
        }
    }
}
