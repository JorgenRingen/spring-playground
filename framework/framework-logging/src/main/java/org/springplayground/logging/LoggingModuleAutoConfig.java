package org.springplayground.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Import;

@Import(LoggingConfiguration.class)
@Configurable
public class LoggingModuleAutoConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingModuleAutoConfig.class);

    public LoggingModuleAutoConfig() {
        LOGGER.debug("Initialising {}", this.getClass().getSimpleName());
    }
}
