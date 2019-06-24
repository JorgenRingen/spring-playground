package org.springplayground.error;

/**
 * <p>
 * This is the parent exception for all system exceptions in an application.
 * </p>
 * <p>
 * A system exception is an exception that is thrown as a result of errors that occur in the runtime platform or operating system that the application is running in.<br>
 * These are error that it is not possible to handle gracefully or recover from.
 * </p>
 */
public class SystemException extends BaseException {

    public SystemException(final String errorCode, final String message) {
        super(errorCode, message);
    }

    public SystemException(final String errorCode, final String message, final Throwable cause) {
        super(errorCode, message, cause);
    }

    public SystemException(final String errorCode, final int httpStatus, final String message) {
        super(errorCode, httpStatus, message);
    }

    public SystemException(final String errorCode, final int httpStatus, final String message, final Throwable cause) {
        super(errorCode, httpStatus, message, cause);
    }
}
