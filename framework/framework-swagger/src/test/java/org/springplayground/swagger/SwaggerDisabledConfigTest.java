package org.springplayground.swagger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@TestPropertySource(properties = "swagger.enabled=false")
@ContextConfiguration
class SwaggerDisabledConfigTest {

    @Autowired
    private SwaggerDisabledConfig swaggerDisabledConfig;

    @Test
    void contextShouldLoad() {
        assertThat(swaggerDisabledConfig).isNotNull();
    }

    @Configuration
    @ComponentScan(basePackageClasses = SwaggerModuleAutoConfig.class)
    static class TestConfiguration {

    }
}
