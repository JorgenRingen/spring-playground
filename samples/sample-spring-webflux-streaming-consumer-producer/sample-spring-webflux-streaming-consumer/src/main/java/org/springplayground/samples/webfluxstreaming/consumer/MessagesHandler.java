package org.springplayground.samples.webfluxstreaming.consumer;

import reactor.core.publisher.Mono;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * Rest-resource declared by using spring-webflux functional style
 */
@Component
public class MessagesHandler {

    private final MessagesClient messagesClient;

    public MessagesHandler(MessagesClient messagesClient) {
        this.messagesClient = messagesClient;
    }

    /**
     * Returns a flux with messages, but the whole response is pushed
     * to the client at once because of application/json.
     */
    Mono<ServerResponse> messages(ServerRequest serverRequest) {
        return messagesClient.messages()
                .flatMap(messages -> ok().contentType(APPLICATION_JSON).body(fromObject(messages)));
    }

    /**
     * Returns server-sent-events by using flux and text/event-stream.
     * Pushes each element to client at a time.
     */
    Mono<ServerResponse> messagesStream(ServerRequest serverRequest) {
        return ok().contentType(TEXT_EVENT_STREAM)
                .body(messagesClient.messagesStream(), Message.class);
    }
}
