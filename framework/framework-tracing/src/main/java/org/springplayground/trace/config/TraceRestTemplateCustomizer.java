package org.springplayground.trace.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import org.springplayground.trace.propagation.TraceRequestInterceptor;

/**
 * <p>
 * Customizer that adds tracing capability to RestTemplates. This customizer is automatically registered with the {@link RestTemplateBuilder org.springframework.boot.web.client.RestTemplateBuilder}
 * so that a {@link RestTemplate org.springframework.web.client.RestTemplate} that is created using the RestTemplateBuilder will apply this customizer.
 * </p>
 * <p>
 * If Spring Cloud Sleuth is used then this class will not be active.
 * </p>
 */
@ConditionalOnMissingClass("org.springframework.cloud.sleuth.instrument.web.client.TraceWebClientAutoConfiguration")
@Configuration
public class TraceRestTemplateCustomizer implements RestTemplateCustomizer {

    private final TraceRequestInterceptor traceRequestInterceptor;

    public TraceRestTemplateCustomizer(final TraceRequestInterceptor traceRequestInterceptor) {
        this.traceRequestInterceptor = traceRequestInterceptor;
    }

    @Override
    public void customize(final RestTemplate restTemplate) {
        restTemplate.getInterceptors().add(traceRequestInterceptor);
    }
}
