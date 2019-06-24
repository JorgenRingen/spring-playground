package com.example.transactionaleventlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TransactionaleventlistenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransactionaleventlistenerApplication.class, args);
    }

    @Autowired
    private MyTransactionalService myTransactionalService;

    @Bean

    public CommandLineRunner commandLineRunner() {
        return args -> {
            myTransactionalService.foo(false);
            myTransactionalService.foo(true);
        };
    }

}
