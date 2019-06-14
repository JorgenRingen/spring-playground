package org.springplayground.samples.tracing;

public class Message {

    private String message;

    public String getMessage() {
        return message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String message;

        private Builder() {
        }

        public Builder message(final String message) {
            this.message = message;
            return this;
        }

        public Message build() {
            final Message message = new Message();
            message.message = this.message;
            return message;
        }
    }
}
