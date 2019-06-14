package org.springplayground.samples.logginggroovyconfigapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class LoggingGroovyConfigApplication {

    public static void main(final String[] args) {
        SpringApplication.run(LoggingGroovyConfigApplication.class, args);
    }

}

