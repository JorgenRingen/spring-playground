package org.springplayground.logging.communication;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;

import org.springplayground.logging.domain.LogFieldHandler;

public class InboundRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws IOException, ServletException {
        LogFieldHandler.setFieldsForIncomingRequest(request);
        CommunicationLogger.logRequestIn(request);
        try {
            filterChain.doFilter(request, response);
        } finally {
            CommunicationLogger.logResponseOut(request, response);
            LogFieldHandler.removeFieldsForIncomingRequest();
        }
    }
}
