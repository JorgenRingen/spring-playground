package org.springplayground.samples.demoapp.integration;

class ShippingFailedException extends RuntimeException {

    ShippingFailedException(String message, Exception cause) {
        super(message, cause);
    }
}
