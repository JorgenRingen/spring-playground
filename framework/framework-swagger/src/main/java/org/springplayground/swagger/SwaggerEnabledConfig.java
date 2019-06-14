package org.springplayground.swagger;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.TagsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

@ConditionalOnProperty(name = "swagger.enabled", havingValue = "true")
@EnableSwagger2
@Configuration
@PropertySource(value = "classpath:/swagger-config.properties")
class SwaggerEnabledConfig implements WebMvcConfigurer {

    @Bean
    public Docket api(@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") final SwaggerConfigurerAdapter swaggerConfigurerAdapter) {
        final Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(swaggerConfigurerAdapter.apiInfo())
                .protocols(swaggerConfigurerAdapter.protocols())
                .consumes(swaggerConfigurerAdapter.consumes())
                .produces(swaggerConfigurerAdapter.produces())
                .forCodeGeneration(swaggerConfigurerAdapter.forCodeGeneration());

        addTags(docket, swaggerConfigurerAdapter.swaggerTags());

        return docket
                .useDefaultResponseMessages(swaggerConfigurerAdapter.useDefaultResponseMessages())
                .select()
                .apis(getApiBasePackages(swaggerConfigurerAdapter))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(0)
                .defaultModelExpandDepth(0)
                .defaultModelRendering(ModelRendering.MODEL)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(true)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .displayRequestDuration(true)
                .build();
    }

    /**
     * Add swagger-ui resources under /swagger-ui
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
        registry.addResourceHandler("/swagger-ui/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * Add redirects for swagger-ui
     */
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addRedirectViewController("/swagger-ui", "/swagger-ui/swagger-ui.html");
        registry.addRedirectViewController("/swagger-ui/", "/swagger-ui/swagger-ui.html");
        registry.addRedirectViewController("/swagger-ui.html", "/swagger-ui/swagger-ui.html");

        registry.addRedirectViewController("/swagger-ui/swagger-api", "/swagger-api").setKeepQueryParams(true);
        registry.addRedirectViewController("/swagger-api/", "/swagger-api").setKeepQueryParams(true);

        registry.addRedirectViewController("/swagger-ui/swagger-model", "/swagger-model").setKeepQueryParams(true);
        registry.addRedirectViewController("/swagger-ui/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
        registry.addRedirectViewController("/swagger-ui/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
        registry.addRedirectViewController("/swagger-ui/swagger-resources", "/swagger-resources");

        registry.addStatusController("/swagger-ui/csrf", HttpStatus.OK);
    }

    private void addTags(final Docket docket, final SwaggerTags swaggerTags) {
        if (swaggerTags == null) {
            return;
        }

        if (swaggerTags.first().isPresent() && !swaggerTags.remaining().isPresent()) {
            docket.tags(swaggerTags.first().get());
        } else if (swaggerTags.first().isPresent() && swaggerTags.remaining().isPresent()) {
            docket.tags(swaggerTags.first().get(), swaggerTags.remaining().get());
        }
    }

    @SuppressWarnings("Guava")
    private Predicate<RequestHandler> getApiBasePackages(final SwaggerConfigurerAdapter swaggerConfigurerAdapter) {
        final List<String> basePackages = swaggerConfigurerAdapter.basePackages();
        if (basePackages.isEmpty()) {
            return RequestHandlerSelectors.none();
        }

        final List<Predicate<RequestHandler>> basePackagePredicates = basePackages.stream()
                .map(RequestHandlerSelectors::basePackage)
                .collect(Collectors.toList());

        return Predicates.or(basePackagePredicates);
    }
}
