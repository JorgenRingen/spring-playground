package org.springplayground.samples.demoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springplayground.samples.demoapp.domain.Order;

public interface OrderRespository extends JpaRepository<Order, Long> {

}
