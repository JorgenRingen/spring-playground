package org.springplayground.logging.performance;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;

import org.springplayground.logging.domain.LogFieldHandler;
import org.springplayground.logging.domain.LogValue;

/**
 * Responsible for logging performance
 */
class PerformanceLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger("PERFORMANCE");

    private PerformanceLogger() {

    }

    static void logRequestIn(final HttpServletRequest request, final long elapsedTimeMillis) {
        try {
            LogFieldHandler.setType(LogValue.REQUEST_IN);
            LogFieldHandler.setElapsedTimeMillis(elapsedTimeMillis);
            LogFieldHandler.setHttpRequestUri(request);
            LogFieldHandler.setHttpRequestMethod(request);
            LogFieldHandler.setHttpRequestQueryString(request);

            log();
        } finally {
            LogFieldHandler.removeType();
            LogFieldHandler.removeElapsedTimeMillis();
            LogFieldHandler.removeHttpRequestUri();
            LogFieldHandler.removeHttpRequestMethod();
            LogFieldHandler.removeHttpRequestQueryString();
        }
    }

    static void logRequestOut(final HttpRequest request, final long elapsedTimeMillis) {
        try {
            LogFieldHandler.setType(LogValue.REQUEST_OUT);
            LogFieldHandler.setElapsedTimeMillis(elapsedTimeMillis);
            LogFieldHandler.setHttpRequestUri(request);
            LogFieldHandler.setHttpRequestMethod(request);
            LogFieldHandler.setHttpRequestQueryString(request);

            log();
        } finally {
            LogFieldHandler.removeType();
            LogFieldHandler.removeElapsedTimeMillis();
            LogFieldHandler.removeHttpRequestUri();
            LogFieldHandler.removeHttpRequestMethod();
            LogFieldHandler.removeHttpRequestQueryString();
        }
    }

    static void logMethodInvocation(final Method method, final long elapsedTimeMillis) {
        try {
            LogFieldHandler.setType(LogValue.METHOD);
            LogFieldHandler.setElapsedTimeMillis(elapsedTimeMillis);
            LogFieldHandler.setMethodSignature(method);

            log();
        } finally {
            LogFieldHandler.removeType();
            LogFieldHandler.removeElapsedTimeMillis();
            LogFieldHandler.removeMethodSignature();
        }
    }

    /*
     * Add methods for SOAP, Kafka, etc if needed
     */

    private static void log() {
        LOGGER.debug("");
    }

}
