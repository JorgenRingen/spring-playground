package org.springplayground.trace.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestTracingResource {

    @GetMapping
    public String test() {
        return "You have been traced!";
    }
}
