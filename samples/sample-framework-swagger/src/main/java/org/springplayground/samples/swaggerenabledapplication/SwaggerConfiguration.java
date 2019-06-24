package org.springplayground.samples.swaggerenabledapplication;

import springfox.documentation.service.ApiInfo;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springplayground.swagger.SwaggerConfigurerAdapter;

@Component
public class SwaggerConfiguration implements SwaggerConfigurerAdapter {

    @Override
    public ApiInfo apiInfo() {
        return ApiInfo.DEFAULT;
    }

    @Override
    public List<String> basePackages() {
        return Collections.singletonList("org.springplayground.samples");
    }
}
