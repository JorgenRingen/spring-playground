package org.springplayground.samples.webfluxstreaming.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class MessagesConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagesConsumerApplication.class, args);
    }

    /**
     * Provides url-mapping through {@link RouterFunction}
     */
    @Bean
    public RouterFunction<?> router(MessagesHandler messagesHandler) {
        return route(GET("/messages"), messagesHandler::messages)
                .andRoute(GET("/messages/stream"), messagesHandler::messagesStream);
    }
}
