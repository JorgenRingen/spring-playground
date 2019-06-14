package org.springplayground.error;

/**
 * <p>
 * This is the parent exception for all application exceptions in an application.
 * </p>
 * <p>
 * An application exception is an exception that is thrown as a result of errors that occur in the application logic or used frameworks.<br>
 * These are errors that it is possible to handle gracefully or recover from within the application logic.
 * </p>
 */
public class ApplicationException extends BaseException {

    public ApplicationException(final String errorCode, final String message) {
        super(errorCode, message);
    }

    public ApplicationException(final String errorCode, final String message, final Throwable cause) {
        super(errorCode, message, cause);
    }

    public ApplicationException(final String errorCode, final int httpStatus, final String message) {
        super(errorCode, httpStatus, message);
    }

    public ApplicationException(final String errorCode, final int httpStatus, final String message, final Throwable cause) {
        super(errorCode, httpStatus, message, cause);
    }
}
