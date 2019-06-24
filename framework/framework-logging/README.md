# Logging

## Purpose
Adds support for
- communication logging
- performance logging

##### Considerations
Uses `spring-boot-starter-web` and `spring-boot-starter-aop` (not required) 

#### Alternatives

## Usage
Add a compile dependency to the module in the applications pom.xml. The module is auto-configured through spring-boot.

For performance-logging on methods through `@EnablePerformanceLogging`, add the following
to an `@Configuration` class in application: `@EnableAspectJAutoProxy(proxyTargetClass = true)`

The module adds several servlet-filters and interceptors in order to do logging.

The interceptors has to be registered in a REST-template in the application. There are `RestTemplateCustomizer` beans that
automatically adds the interceptors to `RestTemplate` that is created using the `RestTemplateBuilder`. Otherwise you
need to register the interceptors manually.

Example:
```
public PingRestConsumer(RestTemplateBuilder restTemplateBuilder,
                            CommunicationlogRestInterceptor communicationlogRestInterceptor,
                            PerformancelogRestInterceptor performancelogRestInterceptor) {
        this.restTemplate = restTemplateBuilder
                .additionalInterceptors(communicationlogRestInterceptor, performancelogRestInterceptor)
                .build();
    }
```

See anctech-platform-samples-logging for usage.

(todo: add re-usable resttemplate in platform) 