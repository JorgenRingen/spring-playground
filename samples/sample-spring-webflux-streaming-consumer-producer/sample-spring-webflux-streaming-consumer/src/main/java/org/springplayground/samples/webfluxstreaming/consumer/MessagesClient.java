package org.springplayground.samples.webfluxstreaming.consumer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Calls the message-producer by using the non-blocking reactive {@link WebClient}
 */
@Component
public class MessagesClient {

    private static final ParameterizedTypeReference<List<Message>> LIST_OF_MESSAGES_TYPE_REFERENCE = new ParameterizedTypeReference<List<Message>>() {
    };

    private final WebClient webClient;

    public MessagesClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081")
                .build();
    }

    Mono<List<Message>> messages() {
        return webClient
                .get()
                .uri("/messages")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ParameterizedTypeReference.forType(LIST_OF_MESSAGES_TYPE_REFERENCE.getType()));
    }

    // consuming without back-pressure
    Flux<Message> messagesStream() {
        return webClient
                .get()
                .uri("/messages/stream")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(Message.class)
                .onErrorResume(e -> Flux.empty());
    }
}
