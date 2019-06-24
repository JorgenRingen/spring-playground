package org.springplayground.error.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springplayground.error.ApplicationException;
import org.springplayground.error.SystemException;

@RestController
public class ErrorThrowingResource {

    @GetMapping(path = "/")
    public String test(@RequestParam(name = "exception", required = false) final String exception) {

        switch (exception) {
            case "ApplicationException":
                throw new ApplicationException("app.0001", 418, "OUCH!");
            case "SystemException":
                throw new SystemException("sys.1337", "BOOM!");
            default:
                return "WOOHOO!";
        }
    }
}
