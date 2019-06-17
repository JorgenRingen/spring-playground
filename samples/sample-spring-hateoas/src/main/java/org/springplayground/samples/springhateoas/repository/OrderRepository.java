package org.springplayground.samples.springhateoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springplayground.samples.springhateoas.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
