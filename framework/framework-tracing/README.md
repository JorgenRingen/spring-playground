# Tracing

## Purpose
This module add tracing capabilities to applications.

#### Considerations

## Usage
This is a Spring Boot enabled module that will automatically bootstrap itself when it is used in an application.

This module handles propagation of tracing information to the applications where it is used. The tracing information is comprised of three different unique IDs,
where each ID has a specific scope and purpose.

* *Conversation ID*: This ID spans several interactions with an application and is used to trace the interaction of individual clients with the application resources.
                     The Conversation ID is not set by the module, but should be supplied by the client.
* *Trace ID*: This ID spans a single interaction with an application. One interaction can result in several internal requests between the application resources. The
              Trace ID is used to correlate all requests that belong to the same interaction.
* *Request ID*: This ID spans a single request to an application resource. If a client interaction results in several requests then each request will have a unique
                Request ID.

HTTP headers are used to propagate tracing information between resources, and between resources and clients.
* `X-Conversation-Id`: This header is used to propagate the *Conversation ID*.
* `X-Trace-Id`: This header is used to propagate the *Trace ID*.
* `X-Request-Id`: This header is used to propagate the *Request ID*.

Inside the application that uses the module the tracing information is propagated using the Mapped Diagnostic Context (MDC) from the logging implementation that is used
in the application. The default logging framework used in Spring Boot applications is Logback, which supports [MDC](https://logback.qos.ch/manual/mdc.html). The tracing
is propagated using the following keys:
* `conversation_id`
* `trace_id`
* `request_id`

If [Spring Cloud Sleuth](https://spring.io/projects/spring-cloud-sleuth) is on the classpath then this module will defer the tracing setup.

## Important classes
These are the important classes that make up the functionality of the module.

#### Trace Request Filter
The `TraceRequestFilter` is responsible for receiving tracing headers from incoming HTTP requests when it is used in a servlet based application.

#### Trace Request Interceptor
The `TraceRequestInterceptor` is responsible for adding tracing headers to outgoing HTTP request when using `RestTemplate`.

#### Trace Propagator
The `TracePropagator` is used to manage tracing information in the application.

#### ID Generator
The `IdGenerator` handles the generation of unique IDs. If [Spring Cloud Sleuth](https://spring.io/projects/spring-cloud-sleuth) is on the classpath then the ID generator
uses the same way to generate unique IDs as is used in Sleuth. Otherwise [java.util.UUID](https://docs.oracle.com/javase/8/docs/api/java/util/UUID.html) is used.
