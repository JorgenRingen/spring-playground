package org.springplayground.logging.domain;

/**
 * Contains "domain specific" http header names.
 * This enum should probably be moved to a more reusable place than logging
 */
@SuppressWarnings("unused")
public enum HttpHeaderNames {

    TRACE_ID("trace-id"),
    X_FORWARDED_FOR("X-Forwarded-For"),
    USER_ID("user-id");

    private final String name;

    HttpHeaderNames(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
