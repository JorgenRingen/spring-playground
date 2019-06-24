package org.springplayground.trace.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "trace")
public class TraceProperties {

    private static final String DEFAULT_SESSION_ID_KEY = "session_id";
    private static final String DEFAULT_CONVERSATION_ID_KEY = "conversation_id";
    private static final String DEFAULT_TRACE_ID_KEY = "trace_id";
    private static final String DEFAULT_INVOCATION_ID_KEY = "invocation_id";
    private static final String DEFAULT_SESSION_ID_HEADER_NAME = "X-SessionId";
    private static final String DEFAULT_CONVERSATION_ID_HEADER_NAME = "X-ConversationId";
    private static final String DEFAULT_TRACE_ID_HEADER_NAME = "X-TraceId";
    public static final String SLEUTH_TRACE_ID_KEY = "traceId";
    public static final String SLEUTH_TRACE_ID_HEADER_NAME = "X-B3-TraceId";

    private String sessionIdKey = DEFAULT_SESSION_ID_KEY;
    private String conversationIdKey = DEFAULT_CONVERSATION_ID_KEY;
    private String traceIdKey = DEFAULT_TRACE_ID_KEY;
    private String invocationIdKey = DEFAULT_INVOCATION_ID_KEY;
    private String sessionIdHeaderName = DEFAULT_SESSION_ID_HEADER_NAME;
    private String conversationIdHeaderName = DEFAULT_CONVERSATION_ID_HEADER_NAME;
    private String traceIdHeaderName = DEFAULT_TRACE_ID_HEADER_NAME;

    public String getSessionIdKey() {
        return sessionIdKey;
    }

    public void setSessionIdKey(String sessionIdKey) {
        this.sessionIdKey = sessionIdKey;
    }

    public String getConversationIdKey() {
        return conversationIdKey;
    }

    public void setConversationIdKey(final String conversationIdKey) {
        this.conversationIdKey = conversationIdKey;
    }

    public String getTraceIdKey() {
        return traceIdKey;
    }

    public void setTraceIdKey(final String traceIdKey) {
        this.traceIdKey = traceIdKey;
    }

    public String getInvocationIdKey() {
        return invocationIdKey;
    }

    public void setInvocationIdKey(String invocationIdKey) {
        this.invocationIdKey = invocationIdKey;
    }

    public String getSessionIdHeaderName() {
        return sessionIdHeaderName;
    }

    public void setSessionIdHeaderName(String sessionIdHeaderName) {
        this.sessionIdHeaderName = sessionIdHeaderName;
    }

    public String getConversationIdHeaderName() {
        return conversationIdHeaderName;
    }

    public void setConversationIdHeaderName(final String conversationIdHeaderName) {
        this.conversationIdHeaderName = conversationIdHeaderName;
    }

    public String getTraceIdHeaderName() {
        return traceIdHeaderName;
    }

    public void setTraceIdHeaderName(final String traceIdHeaderName) {
        this.traceIdHeaderName = traceIdHeaderName;
    }
}
