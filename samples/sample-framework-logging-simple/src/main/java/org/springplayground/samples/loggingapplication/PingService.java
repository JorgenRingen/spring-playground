package org.springplayground.samples.loggingapplication;

import org.springframework.stereotype.Service;
import org.springplayground.logging.performance.EnablePerformanceLogging;

@Service
public class PingService {

    private final PingRestConsumer pingRestConsumer;

    public PingService(final PingRestConsumer pingRestConsumer) {
        this.pingRestConsumer = pingRestConsumer;
    }

    @EnablePerformanceLogging
    public String ping() {
        zZz();
        return pingRestConsumer.ping();
    }

    private void zZz() {
        try {
            Thread.sleep(150);
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
