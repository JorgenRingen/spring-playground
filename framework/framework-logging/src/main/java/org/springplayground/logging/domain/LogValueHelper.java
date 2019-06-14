package org.springplayground.logging.domain;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.lang.NonNull;

/**
 * Helper methods for retrieval of log-field values
 */
class LogValueHelper {

    private static final String DEFAULT_EMPTY_VALUE = "";

    private LogValueHelper() {

    }

    /**
     * Retrieve trace-id header from {@link HttpServletRequest} or create a random one
     */
    static String getTraceId(@NonNull final HttpServletRequest request) {
        return getHeaderFromRequest(request, HttpHeaderNames.TRACE_ID.toString())
                .orElse(UUID.randomUUID().toString());
    }

    static String getSrcIp(@NonNull final HttpServletRequest request) {
        return getHeaderFromRequest(request, HttpHeaderNames.X_FORWARDED_FOR.toString())
                .orElse(DEFAULT_EMPTY_VALUE);
    }

    static String getLoggedInUserId(@NonNull final HttpServletRequest request) {
        return getHeaderFromRequest(request, HttpHeaderNames.USER_ID.toString())
                .orElse(DEFAULT_EMPTY_VALUE);
    }

    static String getHttpRequestUri(@NonNull final HttpRequest request) {
        return Optional.ofNullable(request).map(HttpRequest::getURI).map(URI::toString).orElse(DEFAULT_EMPTY_VALUE);
    }

    static String getHttpRequestMethod(@NonNull final HttpRequest request) {
        return Optional.ofNullable(request.getMethod()).map(HttpMethod::name).orElse(DEFAULT_EMPTY_VALUE);
    }

    static String getHttpRequestQueryString(@NonNull final HttpRequest request) {
        return Optional.ofNullable(request).map(HttpRequest::getURI).map(URI::getQuery).orElse(DEFAULT_EMPTY_VALUE);
    }

    static String getMethodSignature(@NonNull final Method method) {
        return method.getDeclaringClass().getName() + "#" + method.getName();
    }

    private static Optional<String> getHeaderFromRequest(@NonNull final HttpServletRequest request, @NonNull final String headerName) {
        return Optional.ofNullable(request.getHeader(headerName));
    }
}
