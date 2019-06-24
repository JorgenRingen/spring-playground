# Error Handling

## Purpose
This module add error handling capabilities to applications. This means handling exceptions that are thrown in
the application code in order to give sensible feedback to users of the application.

#### Considerations

## Usage
This is a Spring Boot enabled module that will automatically bootstrap itself when it is used in an application.

#### Exceptions
Two root exceptions are defined that should be extended when defining custom exceptions in an application.
* `ApplicationException`: An application exception is an exception that is thrown as a result of errors that occur
                          in the application logic or used frameworks. These are errors that it is possible to handle
                          gracefully or recover from within the application logic.
* `SystemException`: A system exception is an exception that is thrown as a result of errors that occur in the
                     runtime platform or operating system that the application is running in. These are error that it
                     is not possible to handle gracefully or recover from.

Both exceptions contain tracing information as well as the standard exception contents.
* `errorCode`: This is meant to be a machine readable code that specifies exactly what error that cased the exception
               to be thrown. The format of the error codes are up to the user of the module to define. A typical
               format is to use some kind of namespace based format. E.g. like `myapp.app.0001` or `myapp.sys.1337`.
               Here we use a prefix plus a serial number, where the prefix is `<system>.<exception type>`.
* `errorId`: This is a unique ID for the specific exception that is generated when the exception is instantiated.

#### Important classes
These are the important classes that make up the functionality of the module.

##### Global Web Exception Handler
The `GlobalWebExceptionHandler` handles the translation of exceptions into a REST-response to the client calling the
                                application. The error response message is the serialized form of the `ErrorEntity`
                                class, which contains the exception information including the tracing information.

**Error response example:**
```json
{
    "timestamp": "1970-01-01T00:00:00.000+01:00",
    "errorId": "9a93a2d86ef603c5",
    "errorCode": "myapp.app.0001",
    "status": 418,
    "error": "I'm a teapot",
    "message": "An application error occurred",
    "path": "/application"
}

```
