package org.springplayground.logging.performance;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class PerformancelogRestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {
        final long startTimeMillis = System.currentTimeMillis();
        final ClientHttpResponse response = execution.execute(request, body);
        final long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
        PerformanceLogger.logRequestOut(request, elapsedTimeMillis);
        return response;
    }
}
