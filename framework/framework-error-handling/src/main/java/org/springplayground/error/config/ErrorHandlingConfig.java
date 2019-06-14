package org.springplayground.error.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * The main configuration class for the error handling module. This class will bootstrap this module into the Spring application where it is used.
 * </p>
 * <p>
 * This class i also Spring Boot auto-configuration enabled.
 * </p>
 */
@ComponentScan({
        "org.springplayground.error.config"
})
@Configuration
public class ErrorHandlingConfig {

}
