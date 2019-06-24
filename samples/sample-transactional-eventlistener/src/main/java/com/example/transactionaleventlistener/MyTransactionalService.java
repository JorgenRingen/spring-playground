package com.example.transactionaleventlistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MyTransactionalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyTransactionalService.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    public MyTransactionalService(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void foo(boolean throwEx) {
        applicationEventPublisher.publishEvent(new MyEvent("Something useful.."));

        if (throwEx) {
            LOGGER.info("Throwing exception for transaction");
            throw new RuntimeException();
        } else {
            LOGGER.info("Finishing transaction without throwing exception");
        }
    }
}
