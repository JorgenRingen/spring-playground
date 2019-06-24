package org.springplayground.trace.propagation;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;

/**
 * Request interceptor that adds tracing headers to outgoing HTTP requests.
 */
public class TraceRequestInterceptor implements ClientHttpRequestInterceptor {

    private final TracePropagator tracePropagator;

    public TraceRequestInterceptor(final TracePropagator tracePropagator) {
        this.tracePropagator = tracePropagator;
    }

    @NonNull
    @Override
    public ClientHttpResponse intercept(@NonNull final HttpRequest request,
                                        @NonNull final byte[] body,
                                        @NonNull final ClientHttpRequestExecution execution) throws IOException {
        tracePropagator.setTraceForOutgoingRequest(request);
        return execution.execute(request, body);
    }
}
