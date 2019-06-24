package org.springplayground.logging.communication;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

/**
 * Customizer that adds communication logging capability to RestTemplates.
 * This customizer is automatically registered with the {@link RestTemplateBuilder org.springframework.boot.web.client.RestTemplateBuilder}
 */
public class CommunicationlogRestTemplateCustomizer implements RestTemplateCustomizer {

    private final CommunicationlogRestInterceptor communicationlogRestInterceptor;

    public CommunicationlogRestTemplateCustomizer(final CommunicationlogRestInterceptor communicationlogRestInterceptor) {
        this.communicationlogRestInterceptor = communicationlogRestInterceptor;
    }

    @Override
    public void customize(final RestTemplate restTemplate) {
        restTemplate.getInterceptors().add(communicationlogRestInterceptor);
    }
}
