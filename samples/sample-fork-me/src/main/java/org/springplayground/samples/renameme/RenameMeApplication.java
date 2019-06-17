package org.springplayground.samples.renameme;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springplayground.samples.renameme.entities.Foo;
import org.springplayground.samples.renameme.repository.FooRepository;

@SpringBootApplication
public class RenameMeApplication {

    private final FooRepository fooRepository;

    public RenameMeApplication(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(RenameMeApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            fooRepository.saveAll(Arrays.asList(
                    Foo.builder()
                            .message(UUID.randomUUID().toString())
                            .build(),
                    Foo.builder()
                            .message(UUID.randomUUID().toString())
                            .build(),
                    Foo.builder()
                            .message(UUID.randomUUID().toString())
                            .build()));
        };
    }
}
