package org.springplayground.logging.domain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Optional;

import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

/**
 * Handles adding and removal of MDC values.
 */
@SuppressWarnings("WeakerAccess")
public class LogFieldHandler {

    private LogFieldHandler() {

    }

    public static void setFieldsForIncomingRequest(@NonNull final HttpServletRequest request) {
        final String srcIp = LogValueHelper.getSrcIp(request);
        if (StringUtils.hasText(srcIp)) {
            addToMdc(LogField.SRC_IP, srcIp);
        }

        final String loggedInUserId = LogValueHelper.getLoggedInUserId(request);
        if (StringUtils.hasText(loggedInUserId)) {
            addToMdc(LogField.USER_ID, loggedInUserId);
        }
    }

    public static void removeFieldsForIncomingRequest() {
        removeFromMdc(LogField.SRC_IP);
        removeFromMdc(LogField.USER_ID);
    }

    public static void setElapsedTimeMillis(final long elapsedTimeMillis) {
        addToMdc(LogField.ELAPSED_TIME_MS, String.valueOf(elapsedTimeMillis));
    }

    public static void removeElapsedTimeMillis() {
        removeFromMdc(LogField.ELAPSED_TIME_MS);
    }

    public static void setType(final LogValue logValue) {
        addToMdc(LogField.TYPE, logValue);
    }

    public static void removeType() {
        removeFromMdc(LogField.TYPE);
    }

    public static void setHttpRequestUri(@NonNull final HttpServletRequest request) {
        Optional.ofNullable(request.getRequestURI())
                .ifPresent(uri -> addToMdc(LogField.HTTP_URI, uri));
    }

    public static void setHttpRequestUri(@NonNull final HttpRequest request) {
        Optional.ofNullable(LogValueHelper.getHttpRequestUri(request))
                .ifPresent(uri -> addToMdc(LogField.HTTP_URI, uri));
    }

    public static void removeHttpRequestUri() {
        removeFromMdc(LogField.HTTP_URI);
    }

    public static void setHttpRequestMethod(@NonNull final HttpServletRequest request) {
        addToMdc(LogField.HTTP_METHOD, request.getMethod());
    }

    public static void setHttpRequestMethod(@NonNull final HttpRequest request) {
        addToMdc(LogField.HTTP_METHOD, LogValueHelper.getHttpRequestMethod(request));
    }

    public static void removeHttpRequestMethod() {
        removeFromMdc(LogField.HTTP_METHOD);
    }

    public static void setHttpRequestQueryString(@NonNull final HttpServletRequest request) {
        final String queryString = request.getQueryString();
        if (StringUtils.hasText(queryString)) {
            addToMdc(LogField.HTTP_QUERY_STRING, queryString);
        }
    }

    public static void setHttpRequestQueryString(@NonNull final HttpRequest request) {
        final String queryString = LogValueHelper.getHttpRequestQueryString(request);
        if (StringUtils.hasText(queryString)) {
            addToMdc(LogField.HTTP_QUERY_STRING, queryString);
        }
    }

    public static void removeHttpRequestQueryString() {
        removeFromMdc(LogField.HTTP_QUERY_STRING);
    }

    public static void setHttpStatus(@NonNull final HttpServletResponse response) {
        addToMdc(LogField.HTTP_STATUS, String.valueOf(response.getStatus()));
    }

    public static void setHttpStatus(@NonNull final ClientHttpResponse response) {
        try {
            addToMdc(LogField.HTTP_STATUS, String.valueOf(response.getStatusCode()));
        } catch (final IOException e) { // NOSONAR (suppress exception and set -1)
            addToMdc(LogField.HTTP_STATUS, "-1");
        }
    }

    public static void removeHttpStatus() {
        removeFromMdc(LogField.HTTP_STATUS);
    }

    public static void setMethodSignature(final Method method) {
        addToMdc(LogField.METHOD_SIGN, LogValueHelper.getMethodSignature(method));
    }

    public static void removeMethodSignature() {
        removeFromMdc(LogField.METHOD_SIGN);
    }

    public static void addToMdc(@NonNull final LogField logField, final LogValue logValue) {
        addToMdc(logField, logValue.toString());
    }

    public static void addToMdc(@NonNull final LogField logField, final String value) {
        MDC.put(logField.toString(), value);
    }

    public static void removeFromMdc(@NonNull final LogField logField) {
        MDC.remove(logField.toString());
    }

    public static Optional<String> getFromMdc(@NonNull final LogField logField) {
        return Optional.ofNullable(MDC.get(logField.toString()));
    }
}
