package org.springplayground.error.web;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.NonNull;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.UrlPathHelper;
import org.springplayground.error.BaseException;
import org.springplayground.trace.generator.IdGenerator;
import org.springplayground.trace.propagation.TracePropagator;

/**
 * This controller advice is an exception handler that processes uncaught exceptions and transforms them into a error response entity that is returned in the web response.
 */
@ControllerAdvice
public class GlobalWebExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalWebExceptionHandler.class);

    private final TracePropagator tracePropagator;
    private final IdGenerator idGenerator;

    public GlobalWebExceptionHandler(final TracePropagator tracePropagator,
                                     final IdGenerator idGenerator) {
        this.tracePropagator = tracePropagator;
        this.idGenerator = idGenerator;
    }

    /**
     * Handling method for custom defined exceptions.
     *
     * @param exception Exception that was thrown.
     * @param request   Web request that resulted in error.
     * @return Error response entity.
     */
    @ExceptionHandler({
            BaseException.class
    })
    public ResponseEntity<Object> handleCustomException(final Exception exception, final WebRequest request) {
        final BaseException baseException = (BaseException) exception;
        final HttpStatus httpStatus = HttpStatus.resolve(baseException.getHttpStatus());
        return handleException(baseException, httpStatus, request);
    }

    /**
     * Handling method for exceptions that should return an HTTP 400 Bad Request.
     *
     * @param exception Exception that was thrown.
     * @param request   Web request that resulted in error.
     * @return Error response entity.
     */
    @ExceptionHandler({
            IllegalArgumentException.class,
            ConstraintViolationException.class
    })
    public ResponseEntity<Object> handleBadRequestException(final Exception exception, final WebRequest request) {
        return handleException(exception, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handling method for exceptions that should return an HTTP 409 Conflict.
     *
     * @param exception Exception that was thrown.
     * @param request   Web request that resulted in error.
     * @return Error response entity.
     */
    @ExceptionHandler({
            IllegalStateException.class
    })
    public ResponseEntity<Object> handleConflictException(final Exception exception, final WebRequest request) {
        return handleException(exception, HttpStatus.CONFLICT, request);
    }

    /**
     * Handling method for exceptions that should return an HTTP 500 Internal Server Error.
     * <p>
     * This is the general handling method for exceptions that are not handled more specifically.
     *
     * @param exception Exception that was thrown.
     * @param request   Web request that resulted in error.
     * @return Error response entity.
     */
    @ExceptionHandler({
            Exception.class
    })
    public ResponseEntity<Object> handleServerErrorException(final Exception exception, final WebRequest request) {
        return handleException(exception, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    // Override method in order to process this exception like the other exceptions, with same error entity returned
    @NonNull
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(@NonNull final HttpRequestMethodNotSupportedException exception,
                                                                         @NonNull final HttpHeaders headers,
                                                                         @NonNull final HttpStatus status,
                                                                         @NonNull final WebRequest request) {
        return handleException(exception, headers, status, request);
    }

    // Override method in order to process this exception like the other exceptions, with same error entity returned
    @NonNull
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(@NonNull final HttpMediaTypeNotSupportedException exception,
                                                                     @NonNull final HttpHeaders headers,
                                                                     @NonNull final HttpStatus status,
                                                                     @NonNull final WebRequest request) {
        return handleException(exception, headers, status, request);
    }

    // Override method in order to process this exception like the other exceptions, with same error entity returned
    @NonNull
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(@NonNull final HttpMediaTypeNotAcceptableException exception,
                                                                      @NonNull final HttpHeaders headers,
                                                                      @NonNull final HttpStatus status,
                                                                      @NonNull final WebRequest request) {
        return handleException(exception, headers, status, request);
    }

    // Override method in order to process this exception like the other exceptions, with same error entity returned
    @NonNull
    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(@NonNull final MissingPathVariableException exception,
                                                               @NonNull final HttpHeaders headers,
                                                               @NonNull final HttpStatus status,
                                                               @NonNull final WebRequest request) {
        return handleException(exception, headers, status, request);
    }

    // Override method in order to process this exception like the other exceptions, with same error entity returned
    @NonNull
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(@NonNull final HttpMessageNotReadableException exception,
                                                                  @NonNull final HttpHeaders headers,
                                                                  @NonNull final HttpStatus status,
                                                                  @NonNull final WebRequest request) {
        return handleException(exception, headers, status, request);
    }

    // Override method in order to process this exception like the other exceptions, with same error entity returned
    @NonNull
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(@NonNull final HttpMessageNotWritableException exception,
                                                                  @NonNull final HttpHeaders headers,
                                                                  @NonNull final HttpStatus status,
                                                                  @NonNull final WebRequest request) {
        return handleException(exception, headers, status, request);
    }

    // Override method in order to process this exception like the other exceptions, with same error entity returned
    @NonNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull final MethodArgumentNotValidException exception,
                                                                  @NonNull final HttpHeaders headers,
                                                                  @NonNull final HttpStatus status,
                                                                  @NonNull final WebRequest request) {
        return handleException(exception, headers, status, request);
    }

    // Processing of exception with no HTTP headers
    private ResponseEntity<Object> handleException(final Exception exception, final HttpStatus httpStatus, final WebRequest request) {
        return handleException(exception, new HttpHeaders(), httpStatus, request);
    }

    /**
     * Processing of exception and building error response entity.
     *
     * @param exception  Exception that was thrown.
     * @param headers    HTTP headers that will be returned with error.
     * @param httpStatus HTTP status that will be returned with error.
     * @param request    Web request that resulted in error.
     * @return Error response entity.
     */
    private ResponseEntity<Object> handleException(final Exception exception, final HttpHeaders headers, final HttpStatus httpStatus, final WebRequest request) {
        final ErrorEntity errorEntity = ErrorEntity.builder()
                .sessionId(tracePropagator.getSessionId().orElse(null))
                .conversationId(tracePropagator.getConversationId().orElse(null))
                .traceId(tracePropagator.getTraceId().orElse(null))
                .invocationId(tracePropagator.getInvocationId().orElse(null))
                .errorId(findErrorId(exception))
                .errorCode(findErrorCode(exception))
                .status(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .message(exception.getLocalizedMessage())
                .path(findRequestPath(request))
                .build();
        LOGGER.error(exception.getLocalizedMessage(), exception);
        return handleExceptionInternal(exception, errorEntity, headers, httpStatus, request);
    }

    /**
     * Resolve error-id from exception, or generate unique id.
     *
     * @param exception Exception that was thrown.
     * @return Error-id.
     */
    private String findErrorId(final Exception exception) {
        if (BaseException.class.isAssignableFrom(exception.getClass())) {
            final BaseException baseException = (BaseException) exception;
            return baseException.getErrorId() != null ? baseException.getErrorId() : idGenerator.generateId();
        } else {
            return idGenerator.generateId();
        }
    }

    /**
     * Resolve error-code from exception.
     *
     * @param exception Exception that was thrown.
     * @return Error-code.
     */
    private String findErrorCode(final Exception exception) {
        if (BaseException.class.isAssignableFrom(exception.getClass())) {
            final BaseException baseException = (BaseException) exception;
            return baseException.getErrorCode();
        } else {
            return null;
        }
    }

    /**
     * Resolve context path that was called.
     *
     * @param request Web request that resulted in error.
     * @return Context path that was called.
     */
    private String findRequestPath(final WebRequest request) {
        if (request instanceof ServletWebRequest) {
            final ServletWebRequest servletWebRequest = (ServletWebRequest) request;
            return new UrlPathHelper().getPathWithinApplication(servletWebRequest.getRequest());
        } else {
            return request.getContextPath();
        }
    }
}
