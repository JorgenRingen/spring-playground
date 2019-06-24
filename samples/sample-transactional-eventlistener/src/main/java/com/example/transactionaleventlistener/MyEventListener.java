package com.example.transactionaleventlistener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class MyEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyEventListener.class);

    @EventListener
    public void processStandardEvent(MyEvent event) {
        LOGGER.info("Standard event");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void processAfterRollback(MyEvent event) {
        LOGGER.info("AFTER_ROLLBACK event");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void processCommit(MyEvent event) {
        LOGGER.info("AFTER_COMMIT event");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION, fallbackExecution = true)
    public void processAfterCompletion(MyEvent event) {
        LOGGER.info("AFTER_COMPLETION event");
    }
}
