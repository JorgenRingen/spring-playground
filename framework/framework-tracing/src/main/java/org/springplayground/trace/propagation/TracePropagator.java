package org.springplayground.trace.propagation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import org.springplayground.trace.config.TraceProperties;
import org.springplayground.trace.generator.IdGenerator;

/**
 * Bean that is used to administer tracing information in application.
 */
@SuppressWarnings({"WeakerAccess", "UnusedReturnValue"})
@Component
public class TracePropagator {

    private final TraceProperties traceProperties;
    private final IdGenerator idGenerator;

    public TracePropagator(final TraceProperties traceProperties,
                           final IdGenerator idGenerator) {
        this.traceProperties = traceProperties;
        this.idGenerator = idGenerator;
    }

    public Optional<String> getTraceValue(final String traceKey) {
        Assert.hasLength(traceKey, "Trace key cannot be null");
        final String traceValue = MDC.get(traceKey);
        return Optional.ofNullable(traceValue);
    }

    public Optional<String> setTraceValue(final String traceKey, final String traceValue) {
        Assert.hasLength(traceKey, "Trace key cannot be null");
        final Optional<String> exitingTraceValue = getTraceValue(traceKey);
        MDC.put(traceKey, traceValue);
        return exitingTraceValue;
    }

    public Optional<String> setTraceValueIfMissing(final String traceKey, final String traceValue) {
        Assert.hasLength(traceKey, "Trace key cannot be null");
        final Optional<String> exitingTraceValue = getTraceValue(traceKey);
        if (exitingTraceValue.isPresent()) {
            return exitingTraceValue;
        } else {
            MDC.put(traceKey, traceValue);
            return Optional.of(traceValue);
        }
    }

    public Optional<String> removeTraceValue(final String traceKey) {
        Assert.hasLength(traceKey, "Trace key cannot be null");
        final Optional<String> exitingTraceValue = getTraceValue(traceKey);
        MDC.remove(traceKey);
        return exitingTraceValue;
    }

    public Optional<String> getSessionId() {
        return getTraceValue(traceProperties.getSessionIdKey());
    }

    public Optional<String> getConversationId() {
        return getTraceValue(traceProperties.getConversationIdKey());
    }

    public Optional<String> getTraceId() {
        Optional<String> traceId = getTraceValue(traceProperties.getTraceIdKey());
        if (traceId.isPresent()) {
            return traceId;
        } else {
            return getTraceValue(TraceProperties.SLEUTH_TRACE_ID_KEY);
        }
    }

    public Optional<String> getInvocationId() {
        return getTraceValue(traceProperties.getInvocationIdKey());
    }

    public void setSessionId(String id) {
        setTraceValueIfMissing(traceProperties.getSessionIdKey(), id);
    }

    public void setConversationId(String id) {
        setTraceValueIfMissing(traceProperties.getConversationIdKey(), id);
    }

    public void setTraceId(String id) {
        setTraceValueIfMissing(traceProperties.getTraceIdKey(), id);

    }

    public void setInvocationId(String id) {
        setTraceValue(traceProperties.getInvocationIdKey(), id);
    }

    public void setTraceForIncomingRequest(final HttpServletRequest request) {
        final Optional<String> sessionIdHeader = Optional.ofNullable(request.getHeader(traceProperties.getSessionIdHeaderName()));
        final Optional<String> conversationIdHeader = Optional.ofNullable(request.getHeader(traceProperties.getConversationIdHeaderName()));
        final Optional<String> sleuthTraceIdHeader = Optional.ofNullable(request.getHeader(TraceProperties.SLEUTH_TRACE_ID_HEADER_NAME));
        final Optional<String> traceIdHeader = Optional.ofNullable(request.getHeader(traceProperties.getTraceIdHeaderName()));
        final String traceId = traceIdHeader.orElseGet(() -> sleuthTraceIdHeader.orElseGet(idGenerator::generateId));
        final String invocationId = idGenerator.generateId();

        sessionIdHeader.ifPresent(this::setSessionId);
        conversationIdHeader.ifPresent(this::setConversationId);
        setTraceId(traceId);
        setInvocationId(invocationId);
    }

    public void setTraceForOutgoingResponse(final HttpServletResponse response) {
        getSessionId().ifPresent(id -> response.setHeader(traceProperties.getSessionIdHeaderName(), id));
        getConversationId().ifPresent(id -> response.setHeader(traceProperties.getConversationIdHeaderName(), id));
        getTraceId().ifPresent(id -> response.setHeader(traceProperties.getTraceIdHeaderName(), id));
    }

    public void removeTraceForIncomingRequest() {
        removeTraceValue(traceProperties.getSessionIdKey());
        removeTraceValue(traceProperties.getConversationIdKey());
        removeTraceValue(traceProperties.getTraceIdKey());
        removeTraceValue(traceProperties.getInvocationIdKey());
    }

    public void setTraceForOutgoingRequest(final HttpRequest request) {
        getSessionId().ifPresent(id -> request.getHeaders().add(traceProperties.getSessionIdHeaderName(), id));
        getConversationId().ifPresent(id -> request.getHeaders().add(traceProperties.getConversationIdHeaderName(), id));
        getTraceId().ifPresent(id -> request.getHeaders().add(traceProperties.getTraceIdHeaderName(), id));
    }
}
