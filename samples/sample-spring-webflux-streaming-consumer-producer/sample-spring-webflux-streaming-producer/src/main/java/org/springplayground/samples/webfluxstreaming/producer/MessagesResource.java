package org.springplayground.samples.webfluxstreaming.producer;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

/**
 * Rest-resource declared by using standard annotations from spring-web
 */
@RestController
public class MessagesResource {

    private static final List<Message> MESSAGES = new ArrayList<>(Arrays.asList(
            new Message("a"),
            new Message("b"),
            new Message("c"),
            new Message("d"),
            new Message("e")));

    /**
     * Returns a flux with messages, but the whole response is pushed
     * to the client at once because of application/json.
     */
    @GetMapping(value = "messages", produces = APPLICATION_JSON_VALUE)
    public Flux<Message> messages() {
        return Flux.fromStream(MESSAGES.stream())
                .delayElements(Duration.ofMillis(200));
    }

    /**
     * Returns server-sent-events by using flux and text/event-stream.
     * Pushes each element to client at a time.
     */
    @GetMapping(value = "messages/stream", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> messagesStream() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<Message> messages = Flux.fromStream(MESSAGES.stream());
        return Flux.zip(messages, interval, (key, value) -> key);
    }
}
