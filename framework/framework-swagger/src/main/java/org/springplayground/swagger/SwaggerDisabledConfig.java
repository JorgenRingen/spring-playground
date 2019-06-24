package org.springplayground.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ConditionalOnMissingBean(SwaggerEnabledConfig.class)
@Configuration
class SwaggerDisabledConfig implements WebMvcConfigurer {

    /**
     * Returns 404 for static swagger-ui resources if swagger is disabled
     *
     * @see SwaggerEnabledConfig
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addStatusController("/swagger-ui.html**", HttpStatus.NOT_FOUND);
        registry.addStatusController("**/*swagger-ui*/**", HttpStatus.NOT_FOUND);
    }
}
