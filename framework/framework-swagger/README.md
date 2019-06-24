# Swagger

## Purpose
Add support for swagger documentation in applications.

Documentation is created runtime through the [springfox-swagger](https://springfox.github.io/springfox/docs/current/)
library. This module configures the plugin in a somewhat opinionated way.

##### Considerations
`springfox-swagger` uses some libraries for classpath-scanning and rendering.
Most noticeable are `com.google.guava:guava` which are transitively added to
the classpath.

#### Alternatives
Some alternatives to provide swagger-documentation runtime are:
- contract-first development of swagger definition where contracts are hosted outside applications
- build-time generation through [Kongchen Swagger Maven Plugin](https://github.com/kongchen/swagger-maven-plugin)

## Usage
Add a compile dependency to the module in the applications pom.xml. The module is auto-configured through spring-boot.

Provide the property `swagger.enabled=true` to enable swagger.

Create a spring bean that implements the `SwaggerConfigurerAdapter` interface
and implement the `apiInfo` and `basepackages` methods. Basepackages is where `springfox-swagger` should
look for RestControllers, models, etc.

URL's:
- `{application-root}/swagger-api`: Swagger JSON API definition
- `{application-root}/swagger-ui`: Swagger UI

[Springfox-swagger documentation](https://springfox.github.io/springfox/docs/current/) provides instructions
on how to annotate RestControllers and models with `swagger-annotation` metadata.
TLDR: `springfox-swagger` automatically scans and renders annotations from `spring-web`.
To add additional documentation, use annotations from `swagger-annotation`.