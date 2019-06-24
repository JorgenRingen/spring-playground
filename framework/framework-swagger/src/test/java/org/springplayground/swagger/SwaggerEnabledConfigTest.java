package org.springplayground.swagger;

import springfox.documentation.service.ApiInfo;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "swagger.enabled=true")
@ContextConfiguration
class SwaggerEnabledConfigTest {

    @Autowired
    private SwaggerEnabledConfig swaggerEnabledConfig;

    @Test
    void contextShouldLoad() {
        assertThat(swaggerEnabledConfig).isNotNull();
    }

    @Configuration
    @ComponentScan(basePackageClasses = SwaggerModuleAutoConfig.class)
    static class TestConfiguration {

        @Bean
        public SwaggerConfigurerAdapter swaggerConfigurerAdapter() {
            return new TestSwaggerConfig();
        }
    }

    static class TestSwaggerConfig implements SwaggerConfigurerAdapter {

        @Override
        public SwaggerTags swaggerTags() {
            return SwaggerTags.builder().build();
        }

        @Override
        public ApiInfo apiInfo() {
            return ApiInfo.DEFAULT;
        }

        @Override
        public List<String> basePackages() {
            return Arrays.asList("org.example", "com.example");
        }
    }
}
