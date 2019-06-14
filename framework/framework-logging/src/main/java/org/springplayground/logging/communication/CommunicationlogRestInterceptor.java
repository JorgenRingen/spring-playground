package org.springplayground.logging.communication;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class CommunicationlogRestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {
        CommunicationLogger.logRequestOut(request);
        final ClientHttpResponse response = execution.execute(request, body);
        CommunicationLogger.logResponseIn(request, response);
        return response;
    }
}
