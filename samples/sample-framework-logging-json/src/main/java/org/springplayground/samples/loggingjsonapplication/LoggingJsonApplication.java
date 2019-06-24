package org.springplayground.samples.loggingjsonapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class LoggingJsonApplication {

    public static void main(final String[] args) {
        SpringApplication.run(LoggingJsonApplication.class, args);
    }

}

