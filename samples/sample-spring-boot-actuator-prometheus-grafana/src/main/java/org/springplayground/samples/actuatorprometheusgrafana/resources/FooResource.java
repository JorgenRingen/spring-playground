package org.springplayground.samples.actuatorprometheusgrafana.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("foo")
public class FooResource {

    @GetMapping
    public List<String> foos() {
        return Collections.singletonList("foo");
    }
}
