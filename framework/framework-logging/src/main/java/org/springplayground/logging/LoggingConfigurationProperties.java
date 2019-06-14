package org.springplayground.logging;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * {@link ConfigurationProperties} enables autocompletion in IDE's.
 * <p>
 * Typical usecases:
 * - enable/disable loggers
 * - disable url's from loggers
 * - etc...
 */
@SuppressWarnings("unused")
@Component
@ConfigurationProperties(prefix = "springplayground.logging")
public class LoggingConfigurationProperties {

}
