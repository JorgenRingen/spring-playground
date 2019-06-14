package org.springplayground.error.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This is the configuration class for web-application. If the module is used in a Spring web-application then this class becomes active.
 */
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ComponentScan({"org.springplayground.error.web"})
@Configuration
public class WebErrorHandlingConfig {

}

