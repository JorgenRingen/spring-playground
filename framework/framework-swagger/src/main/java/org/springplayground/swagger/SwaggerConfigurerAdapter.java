package org.springplayground.swagger;

import springfox.documentation.service.ApiInfo;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Interface should be implemented and provided as a spring bean by the application
 *
 * @see SwaggerEnabledConfig
 */
public interface SwaggerConfigurerAdapter {

    default SwaggerTags swaggerTags() {
        return SwaggerTags.builder().build();
    }

    ApiInfo apiInfo();

    List<String> basePackages();

    default Set<String> protocols() {
        return Collections.emptySet();
    }

    default boolean forCodeGeneration() {
        return true;
    }

    default boolean useDefaultResponseMessages() {
        return true;
    }

    default Set<String> consumes() {
        return Collections.singleton("application/json");
    }

    default Set<String> produces() {
        return Collections.singleton("application/json");
    }
}
