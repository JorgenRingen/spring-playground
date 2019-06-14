package org.springplayground.logging.performance;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

public class PerformancelogInboundRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
        final long startTimeMillis = System.currentTimeMillis();
        try {
            filterChain.doFilter(request, response);
        } finally {
            final long elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
            PerformanceLogger.logRequestIn(request, elapsedTimeMillis);
        }
    }
}
