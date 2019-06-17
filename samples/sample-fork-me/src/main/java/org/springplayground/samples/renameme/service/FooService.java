package org.springplayground.samples.renameme.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springplayground.samples.renameme.entities.Foo;
import org.springplayground.samples.renameme.repository.FooRepository;

@Service
@Transactional
public class FooService {

    private final FooRepository fooRepository;

    public FooService(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    @Transactional
    public List<Foo> foos() {
        return fooRepository.findAll();
    }
}
