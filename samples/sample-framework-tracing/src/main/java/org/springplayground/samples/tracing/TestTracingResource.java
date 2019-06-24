package org.springplayground.samples.tracing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestTracingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestTracingResource.class);

    @GetMapping
    public Message get() {
        LOGGER.info("You have been traced!");
        return Message.builder()
                .message("You have been traced!")
                .build();
    }
}
