package org.springplayground.samples.loggingapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class LoggingApplication {

    public static void main(final String[] args) {
        SpringApplication.run(LoggingApplication.class, args);
    }

}

