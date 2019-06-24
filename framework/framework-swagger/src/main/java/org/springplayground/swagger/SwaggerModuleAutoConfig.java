package org.springplayground.swagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.springplayground.swagger")
@Configurable
public class SwaggerModuleAutoConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerModuleAutoConfig.class);

    public SwaggerModuleAutoConfig() {
        LOGGER.debug("Initialising {}", this.getClass().getSimpleName());
    }
}
