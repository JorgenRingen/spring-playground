package org.springplayground.logging.domain;

public enum LogValue {

    // communication
    REQUEST_IN("REQUEST_IN"),
    RESPONSE_OUT("RESPONSE_OUT"),
    REQUEST_OUT("REQUEST_OUT"),
    REPONSE_IN("RESPONSE_IN"),

    // aspect
    METHOD("METHOD");

    private final String name;

    LogValue(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
