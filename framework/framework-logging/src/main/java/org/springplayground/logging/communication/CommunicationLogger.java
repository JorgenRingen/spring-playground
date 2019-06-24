package org.springplayground.logging.communication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;

import org.springplayground.logging.domain.LogFieldHandler;
import org.springplayground.logging.domain.LogValue;

/**
 * Responsible for logging of all communication (inbound and outbound).
 */
class CommunicationLogger {

    private static final Logger LOGGER = LoggerFactory.getLogger("COMMUNICATION");

    private CommunicationLogger() {

    }

    /*
     * HTTP request in/out
     */
    static void logRequestIn(@NonNull final HttpServletRequest request) {
        try {
            LogFieldHandler.setType(LogValue.REQUEST_IN);
            LogFieldHandler.setHttpRequestUri(request);
            LogFieldHandler.setHttpRequestMethod(request);
            LogFieldHandler.setHttpRequestQueryString(request);

            log();
        } finally {
            LogFieldHandler.removeType();
            LogFieldHandler.removeHttpRequestUri();
            LogFieldHandler.removeHttpRequestMethod();
            LogFieldHandler.removeHttpRequestQueryString();
        }
    }

    static void logResponseOut(@NonNull final HttpServletRequest request, @NonNull final HttpServletResponse response) {
        try {
            LogFieldHandler.setType(LogValue.RESPONSE_OUT);
            LogFieldHandler.setHttpRequestUri(request);
            LogFieldHandler.setHttpRequestMethod(request);
            LogFieldHandler.setHttpRequestQueryString(request);
            LogFieldHandler.setHttpStatus(response);

            log();
        } finally {
            LogFieldHandler.removeType();
            LogFieldHandler.removeHttpRequestUri();
            LogFieldHandler.removeHttpRequestMethod();
            LogFieldHandler.removeHttpRequestQueryString();
            LogFieldHandler.removeHttpStatus();
        }
    }

    /*
     * REST out/in
     */
    static void logRequestOut(final HttpRequest request) {
        try {
            LogFieldHandler.setType(LogValue.REQUEST_OUT);
            LogFieldHandler.setHttpRequestUri(request);
            LogFieldHandler.setHttpRequestMethod(request);
            LogFieldHandler.setHttpRequestQueryString(request);

            log();
        } finally {
            LogFieldHandler.removeType();
            LogFieldHandler.removeHttpRequestUri();
            LogFieldHandler.removeHttpRequestMethod();
            LogFieldHandler.removeHttpRequestQueryString();
        }
    }

    static void logResponseIn(final HttpRequest request, final ClientHttpResponse response) {
        try {
            LogFieldHandler.setType(LogValue.REPONSE_IN);
            LogFieldHandler.setHttpStatus(response);
            LogFieldHandler.setHttpRequestUri(request);

            log();
        } finally {
            LogFieldHandler.removeType();
            LogFieldHandler.removeHttpStatus();
            LogFieldHandler.removeHttpRequestUri();
        }
    }

    /*
     * Add methods for SOAP, Kafka, etc if needed
     */

    /**
     * Logs empty message with DEBUG level.
     * Consider adding functionality for logging headers, payload, etc.
     */
    private static void log() {
        LOGGER.debug("");
    }
}
