package org.springplayground.samples.logginggroovyconfigapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ping")
public class PingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingResource.class);

    private final PingService pingService;

    public PingResource(final PingService pingService) {
        this.pingService = pingService;
    }

    @GetMapping
    public String ping() {
        LOGGER.info("Received ping request");
        return pingService.ping();
    }
}
