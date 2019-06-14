package org.springplayground.logging.mdc;

import java.util.Map;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;

/**
 * Propagate MDC-context to async threads {@code @Async} to make
 * MDC-fields available.
 * <p>
 * Add to async executor in application:
 * {@code simpleAsyncTaskExecutor.setTaskDecorator(new MdcTaskDecorator());}
 */
@SuppressWarnings("unused")
public class MdcTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(final Runnable runnable) {
        final Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return () -> {
            try {
                if (contextMap != null) {
                    MDC.setContextMap(contextMap);
                }
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}