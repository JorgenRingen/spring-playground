package org.springplayground.samples.loggingapplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ping")
public class PingResource {

    private final PingService pingService;

    public PingResource(final PingService pingService) {
        this.pingService = pingService;
    }

    @GetMapping
    public String hello() {
        return pingService.ping();
    }
}
