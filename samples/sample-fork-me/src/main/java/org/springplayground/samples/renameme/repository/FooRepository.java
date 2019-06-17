package org.springplayground.samples.renameme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springplayground.samples.renameme.entities.Foo;

public interface FooRepository extends JpaRepository<Foo, Long> {

}
