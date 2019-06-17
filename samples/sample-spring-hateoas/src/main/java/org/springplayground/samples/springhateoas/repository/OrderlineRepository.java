package org.springplayground.samples.springhateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springplayground.samples.springhateoas.domain.Orderline;

public interface OrderlineRepository extends JpaRepository<Orderline, Long> {

}
