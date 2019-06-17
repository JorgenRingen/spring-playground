package org.springplayground.samples.renameme.resources;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springplayground.samples.renameme.entities.Foo;
import org.springplayground.samples.renameme.service.FooService;

@RestController
@RequestMapping("foo")
public class FooResource {

    private final FooService fooService;

    public FooResource(FooService fooService) {
        this.fooService = fooService;
    }

    @GetMapping
    public List<Foo> foos() {
        return fooService.foos();
    }
}
