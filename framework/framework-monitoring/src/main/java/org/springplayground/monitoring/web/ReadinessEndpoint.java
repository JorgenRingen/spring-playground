package org.springplayground.monitoring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;
import org.springplayground.monitoring.probe.ReadinessProbe;

/**
 * Actuator-endpoint for checking is application is ready and for manually triggering readiness
 */
@Component
@Endpoint(id = "readiness")
public class ReadinessEndpoint {

    private final ReadinessProbe readinessProbe;
    private boolean isEnabled = true;

    @Autowired
    public ReadinessEndpoint(final ReadinessProbe readinessProbe) {
        this.readinessProbe = readinessProbe;
    }

    @ReadOperation
    public Health alive() {
        return isEnabled && readinessProbe.isReady() ? Health.up().build() : Health.down().build();
    }

    @WriteOperation
    public void triggerReadiness() {
        this.isEnabled = !isEnabled;
    }

}
