package com.example.transactionaleventlistener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransactionaleventlistenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionaleventlistenerApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(MyTransactionalService myTransactionalService) {
        return args -> {
            myTransactionalService.foo(false);
            try {
                myTransactionalService.foo(true);
            } catch (RuntimeException e) {
                // do nothing...
            }
        };
    }

}
