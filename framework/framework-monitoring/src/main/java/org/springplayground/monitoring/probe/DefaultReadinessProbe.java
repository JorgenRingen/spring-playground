package org.springplayground.monitoring.probe;

/**
 * This is the default readiness probe that is used if the application does not define its own.
 */
public class DefaultReadinessProbe implements ReadinessProbe {

    @Override
    public boolean isReady() {
        return true;
    }
}
