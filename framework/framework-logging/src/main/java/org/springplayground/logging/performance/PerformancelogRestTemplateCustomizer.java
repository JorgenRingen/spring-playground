package org.springplayground.logging.performance;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

/**
 * Customizer that adds performance logging capability to RestTemplates.
 * This customizer is automatically registered with the {@link RestTemplateBuilder org.springframework.boot.web.client.RestTemplateBuilder}.
 */
public class PerformancelogRestTemplateCustomizer implements RestTemplateCustomizer {

    private final PerformancelogRestInterceptor performancelogRestInterceptor;

    public PerformancelogRestTemplateCustomizer(final PerformancelogRestInterceptor performancelogRestInterceptor) {
        this.performancelogRestInterceptor = performancelogRestInterceptor;
    }

    @Override
    public void customize(final RestTemplate restTemplate) {
        restTemplate.getInterceptors().add(performancelogRestInterceptor);
    }
}
