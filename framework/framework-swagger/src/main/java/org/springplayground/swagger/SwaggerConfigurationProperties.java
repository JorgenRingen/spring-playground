package org.springplayground.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * {@link ConfigurationProperties} enables autocompletion in IDE's.
 */
@SuppressWarnings("unused")
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfigurationProperties {

    /**
     * Enables swagger
     */
    private Boolean enabled;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }
}
