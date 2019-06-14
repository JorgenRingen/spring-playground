package org.springplayground.samples.tracing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestTracingResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestTracingResource.class);

    private final String outgoingUrl;
    private final RestTemplate restTemplate;

    public TestTracingResource(@Value("${outgoing.url}") final String outgoingUrl,
                               final RestTemplateBuilder templateBuilder) {
        this.outgoingUrl = outgoingUrl;
        this.restTemplate = templateBuilder.build();
    }

    @GetMapping
    public Message get() {
        LOGGER.info("You have been traced!");
        return Message.builder()
                .message("You have been traced!")
                .build();
    }

    @GetMapping(path = "outgoing")
    public Message getOutgoing() {
        LOGGER.info("You have been traced!");
        return restTemplate.getForObject(outgoingUrl, Message.class);
    }
}
