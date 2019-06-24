# Monitoring

## Purpose
Add support for readiness and liveness monitoring i applications.

## Usage
Add a compile dependency to the module in the applications pom.xml. The module is auto-configured through spring-boot.

Provide the property `management.endpoints.web.exposure.include=liveness, readiness` to expose the endpoints over http, 
or use `management.endpoints.web.exposure.include=*` to expose all actuator endpoints. 

The endpoints will also be exposed over JMX. 

The application can create a bean that implements the `ReadinessProbe` interface to define it's own logic for when the application is ready.
If no bean is found in the application then the `DefaultReadinessProbe` is used. The default currently only returns true, but should be modified according the the project use-case. 

URL's:
- `{application-root}/actuator/liveness`: Liveness endpoint
- `{application-root}/actuator/readiness`: readiness endpoint
