package org.springplayground.samples.webfluxstreaming.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MessagesProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessagesProducerApplication.class, args);
    }
}
