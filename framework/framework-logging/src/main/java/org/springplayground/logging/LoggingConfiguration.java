package org.springplayground.logging;

import java.util.Collections;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.client.RestTemplate;
import org.springplayground.logging.communication.InboundRequestFilter;
import org.springplayground.logging.performance.PerformancelogRestTemplateCustomizer;

import org.springplayground.logging.communication.CommunicationlogRestInterceptor;
import org.springplayground.logging.communication.CommunicationlogRestTemplateCustomizer;
import org.springplayground.logging.performance.PerformancelogAspect;
import org.springplayground.logging.performance.PerformancelogInboundRequestFilter;
import org.springplayground.logging.performance.PerformancelogRestInterceptor;

@ConditionalOnWebApplication
@Configuration
public class LoggingConfiguration {

    @Bean
    public InboundRequestFilter inboundRequestFilter() {
        return new InboundRequestFilter();
    }

    @Bean
    public FilterRegistrationBean<InboundRequestFilter> inboundRequestFilterFilterRegistrationBean() {
        final FilterRegistrationBean<InboundRequestFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(inboundRequestFilter());
        filterRegistration.setUrlPatterns(Collections.singleton("/*"));
        filterRegistration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return filterRegistration;
    }

    @Bean
    public PerformancelogInboundRequestFilter performancelogInboundRequestFilter() {
        return new PerformancelogInboundRequestFilter();
    }

    @Bean
    public FilterRegistrationBean<PerformancelogInboundRequestFilter> performancelogInboundRequestFilterRegistrationBean() {
        final FilterRegistrationBean<PerformancelogInboundRequestFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(performancelogInboundRequestFilter());
        filterRegistration.setUrlPatterns(Collections.singleton("/*"));
        filterRegistration.setOrder(Ordered.HIGHEST_PRECEDENCE + 1); // after InboundRequestFilter
        return filterRegistration;
    }

    @Bean
    public PerformancelogAspect performancelogAspect() {
        return new PerformancelogAspect();
    }

    @Bean
    public CommunicationlogRestTemplateCustomizer communicationlogRestTemplateCustomizer() {
        return new CommunicationlogRestTemplateCustomizer(communicationlogRestInterceptor());
    }

    @Bean
    public PerformancelogRestTemplateCustomizer performancelogRestTemplateCustomizer() {
        return new PerformancelogRestTemplateCustomizer(performancelogRestInterceptor());
    }

    /**
     * Bean should be added as interceptor to {@link RestTemplate org.springframework.web.client.RestTemplate}
     */
    @Bean
    public CommunicationlogRestInterceptor communicationlogRestInterceptor() {
        return new CommunicationlogRestInterceptor();
    }

    /**
     * Bean should be added as interceptor to {@link RestTemplate org.springframework.web.client.RestTemplate}
     */
    @Bean
    public PerformancelogRestInterceptor performancelogRestInterceptor() {
        return new PerformancelogRestInterceptor();
    }
}
