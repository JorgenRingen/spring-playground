package org.springplayground.logging.domain;

/**
 * Fields that should be configured in {@code logback.xml} and used in patterns.
 * Fields are added to {@link org.slf4j.MDC} either through framework capabilities
 * in this module or by application code.
 */
@SuppressWarnings("unused")
public enum LogField {

    // once per request
    SRC_IP("src_ip"),
    USER_ID("user_id"),

    // communication
    HTTP_URI("http_uri"),
    HTTP_METHOD("http_method"),
    HTTP_QUERY_STRING("http_query_string"),
    HTTP_HEADERS("http_headers"),
    HTTP_STATUS("http_status"),

    // performance
    ELAPSED_TIME_MS("elapsed_time_ms"),
    CLASS_NAME("class_name"),
    METHOD_SIGN("method_sign"),

    // security
    USER_NAME("user_name"),
    ROLES("roles"),

    // domain specific fields (case_id, user_id, etc)
    TYPE("type");

    private final String name;

    LogField(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
