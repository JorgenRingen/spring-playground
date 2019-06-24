package org.springplayground.trace.generator;

import brave.internal.Platform;
import brave.propagation.TraceIdContext;

/**
 * <p>
 * This is an ID generator that uses {@link Platform brave.internal.Platform} from the Brave framework to generate IDs.
 * </p>
 * <p>
 * Brave is also used by <a href="https://spring.io/projects/spring-cloud-sleuth" target="_blank">Spring Cloud Sleuth</a> to generate tracing IDs.
 * </p>
 */
public class SleuthIdGenerator implements IdGenerator {

    /**
     * This flag specifies if the generated ID should be a 128 bit or 64 bit ID.
     */
    private final boolean traceId128;

    SleuthIdGenerator(final boolean traceId128) {
        this.traceId128 = traceId128;
    }

    @Override
    public String generateId() {
        final long id64bit = nextId64bit();
        final long id128bit = nextId128bit();
        final TraceIdContext traceIdContext = TraceIdContext.newBuilder()
                .traceId(id64bit)
                .traceIdHigh(id128bit)
                .build();
        return traceIdContext.toString();
    }

    /**
     * Method to generate 64 bit traceId.
     *
     * @return 64 bit traceId.
     */
    @SuppressWarnings("StatementWithEmptyBody")
    private long nextId64bit() {
        long nextId;
        for (nextId = Platform.get().randomLong(); nextId == 0L; nextId = Platform.get().randomLong()) {
        }
        return nextId;
    }

    /**
     * Method to generate 128 bit traceId. If 128 bit flag is false this method returns a zero, which causes the Brave-platform
     * to use the 64 bit traceId.
     *
     * @return 128 bit traceId.
     */
    private long nextId128bit() {
        return traceId128 ? Platform.get().nextTraceIdHigh() : 0L;
    }
}
