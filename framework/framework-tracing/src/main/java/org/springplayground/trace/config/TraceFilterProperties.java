package org.springplayground.trace.config;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;

@ConfigurationProperties(prefix = "trace.filter")
public class TraceFilterProperties {

    private static final int DEFAULT_FILTER_ORDER = Ordered.HIGHEST_PRECEDENCE;
    private static final List<String> DEFAULT_FILTER_URL_PATTERNS = Collections.singletonList("/*");

    private int order = DEFAULT_FILTER_ORDER;
    private List<String> urlPatterns = DEFAULT_FILTER_URL_PATTERNS;

    public int getOrder() {
        return order;
    }

    public void setOrder(final int order) {
        this.order = order;
    }

    public List<String> getUrlPatterns() {
        return urlPatterns;
    }

    public void setUrlPatterns(final List<String> urlPatterns) {
        this.urlPatterns = urlPatterns;
    }
}
