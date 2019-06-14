package org.springplayground.trace.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springplayground.trace.generator.IdGeneratorFactory;

/**
 * <p>
 * The main configuration class for the tracing module. This class will bootstrap this module into the Spring application where it is used.
 * </p>
 * <p>
 * This class i also Spring Boot auto-configuration enabled.
 * </p>
 */
@EnableConfigurationProperties(TraceProperties.class)
@ComponentScan("org.springplayground.trace")
@Configuration
public class TraceConfig {

    @Bean
    public IdGeneratorFactory idGenerator(@Value("${spring.sleuth.trace-id128:false}") final boolean traceId128) {
        return new IdGeneratorFactory(traceId128);
    }
}
