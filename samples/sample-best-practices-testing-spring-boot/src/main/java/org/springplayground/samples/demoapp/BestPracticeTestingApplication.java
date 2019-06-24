package org.springplayground.samples.demoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class BestPracticeTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BestPracticeTestingApplication.class, args);
    }

}

