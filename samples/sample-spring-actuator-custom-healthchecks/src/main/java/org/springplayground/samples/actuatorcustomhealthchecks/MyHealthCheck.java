package org.springplayground.samples.actuatorcustomhealthchecks;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up()
                .withDetail("testKey", "testValue")
                .build();
    }
}
