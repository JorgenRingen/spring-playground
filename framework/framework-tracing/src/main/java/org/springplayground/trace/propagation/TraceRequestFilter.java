package org.springplayground.trace.propagation;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Servlet request filter that receives tracing headers from incoming HTTP requests.
 */
public class TraceRequestFilter extends OncePerRequestFilter {

    private final TracePropagator tracePropagator;

    public TraceRequestFilter(final TracePropagator tracePropagator) {
        this.tracePropagator = tracePropagator;
    }

    @Override
    protected void doFilterInternal(@NonNull final HttpServletRequest request,
                                    @NonNull final HttpServletResponse response,
                                    @NonNull final FilterChain filterChain) throws IOException, ServletException {
        tracePropagator.setTraceForIncomingRequest(request);
        try {
            tracePropagator.setTraceForOutgoingResponse(response);
            filterChain.doFilter(request, response);
        } finally {
            tracePropagator.removeTraceForIncomingRequest();
        }
    }
}
