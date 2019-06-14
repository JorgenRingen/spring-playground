package org.springplayground.trace.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springplayground.trace.propagation.TracePropagator;
import org.springplayground.trace.propagation.TraceRequestFilter;
import org.springplayground.trace.propagation.TraceRequestInterceptor;

/**
 * <p>
 * Configuration class to enable tracing capability to application.
 * </p>
 * <p>
 * If Spring Cloud Sleuth is used then this class will not be active.
 * </p>
 */
@EnableConfigurationProperties(TraceFilterProperties.class)
@ConditionalOnMissingClass("org.springframework.cloud.sleuth.autoconfig.TraceAutoConfiguration")
@Configuration
public class TraceWebConfig {

    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @Bean
    public TraceRequestFilter traceRequestFilter(final TracePropagator tracePropagator) {
        return new TraceRequestFilter(tracePropagator);
    }

    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    @Bean
    public FilterRegistrationBean<TraceRequestFilter> traceRequestFilterRegistrationBean(final TraceFilterProperties traceFilterProperties,
                                                                                         final TraceRequestFilter traceRequestFilter) {
        final FilterRegistrationBean<TraceRequestFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(traceRequestFilter);
        filterRegistration.setOrder(traceFilterProperties.getOrder());
        filterRegistration.setUrlPatterns(traceFilterProperties.getUrlPatterns());
        return filterRegistration;
    }

    @Bean
    public TraceRequestInterceptor traceRequestInterceptor(final TracePropagator tracePropagator) {
        return new TraceRequestInterceptor(tracePropagator);
    }
}
