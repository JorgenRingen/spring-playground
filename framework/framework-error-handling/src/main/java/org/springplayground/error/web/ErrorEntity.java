package org.springplayground.error.web;

import java.time.ZonedDateTime;

/**
 * This is the entity class that is the representation of an error that is returned in the response from the web error handler.
 */
@SuppressWarnings({"WeakerAccess"})
public class ErrorEntity {

    private ZonedDateTime timestamp;
    private String sessionId;
    private String conversationId;
    private String traceId;
    private String invocationId;
    private String errorId;
    private String errorCode;
    private int status;
    private String error;
    private String message;
    private String path;

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public String getTraceId() {
        return traceId;
    }

    public String getInvocationId() {
        return invocationId;
    }

    public String getErrorId() {
        return errorId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public static Builder builder() {
        return new Builder();
    }

    /**
     * Builder for error entity objects.
     */
    public static final class Builder {

        private String sessionId;
        private String conversationId;
        private String traceId;
        private String invocationId;
        private String errorId;
        private String errorCode;
        private int status;
        private String error;
        private String message;
        private String path;

        private Builder() {
        }

        public Builder sessionId(final String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        public Builder conversationId(final String conversationId) {
            this.conversationId = conversationId;
            return this;
        }

        public Builder traceId(final String traceId) {
            this.traceId = traceId;
            return this;
        }

        public Builder invocationId(final String invocationId) {
            this.invocationId = invocationId;
            return this;
        }

        public Builder errorId(final String errorId) {
            this.errorId = errorId;
            return this;
        }

        public Builder errorCode(final String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder status(final int status) {
            this.status = status;
            return this;
        }

        public Builder error(final String error) {
            this.error = error;
            return this;
        }

        public Builder message(final String message) {
            this.message = message;
            return this;
        }

        public Builder path(final String path) {
            this.path = path;
            return this;
        }

        public ErrorEntity build() {
            final ErrorEntity errorEntity = new ErrorEntity();
            errorEntity.timestamp = ZonedDateTime.now();
            errorEntity.sessionId = this.sessionId;
            errorEntity.conversationId = this.conversationId;
            errorEntity.traceId = this.traceId;
            errorEntity.invocationId = this.invocationId;
            errorEntity.errorId = this.errorId;
            errorEntity.errorCode = this.errorCode;
            errorEntity.error = this.error;
            errorEntity.status = this.status;
            errorEntity.message = this.message;
            errorEntity.path = this.path;
            return errorEntity;
        }
    }
}
