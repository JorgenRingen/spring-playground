package org.springplayground.monitoring.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springplayground.monitoring.probe.DefaultReadinessProbe;
import org.springplayground.monitoring.probe.ReadinessProbe;

/**
 * <p>
 * The main configuration class for the monitoring module. This class will bootstrap this module into the Spring application where it is used.
 * </p>
 * <p>
 * This class is also Spring Boot auto-configuration enabled.
 * </p>
 */
@ComponentScan({
        "org.springplayground.monitoring.web"
})
@Configuration
public class MonitoringConfig {

    @ConditionalOnMissingBean
    @Bean
    public ReadinessProbe readinessProbe() {
        return new DefaultReadinessProbe();
    }

}
