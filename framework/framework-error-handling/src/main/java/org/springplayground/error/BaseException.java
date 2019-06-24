package org.springplayground.error;

import org.springplayground.trace.generator.IdGeneratorFactory;

/**
 * <p>
 * This is the parent exception for all custom exceptions that are defined in an application. This class defines all fields required for custom exceptions.
 * </p>
 * <p>
 * No custom exception should extend this exception directly, but should instead extend one of the two child exceptions of this exception:
 * </p>
 * <ul>
 * <li>{@link ApplicationException}</li>
 * <li>{@link SystemException}</li>
 * </ul>
 */
public class BaseException extends RuntimeException {

    private final String errorId;
    private final String errorCode;
    private int httpStatus = 500; // Default HTTP status is 500 Internal Server Error

    BaseException(final String errorCode, final String message) {
        super(message);
        this.errorId = IdGeneratorFactory.create().generateId();
        this.errorCode = errorCode;
    }

    BaseException(final String errorCode, final String message, final Throwable cause) {
        super(message, cause);
        this.errorId = IdGeneratorFactory.create().generateId();
        this.errorCode = errorCode;
    }

    BaseException(final String errorCode, final int httpStatus, final String message) {
        this(errorCode, message);
        this.httpStatus = httpStatus;
    }

    BaseException(final String errorCode, final int httpStatus, final String message, final Throwable cause) {
        this(errorCode, message, cause);
        this.httpStatus = httpStatus;
    }

    public String getErrorId() {
        return errorId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
