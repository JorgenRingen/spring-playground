package org.springplayground.monitoring.probe;

/**
 * <p>
 * This interface can be implemented by the application to define the conditions for when the application is considered ready.
 * </p>
 * <p>
 * If the application does not implement this interface then the {@link DefaultReadinessProbe} is used.
 * </p>
 *
 * @see DefaultReadinessProbe
 */
public interface ReadinessProbe {

    boolean isReady();

}
