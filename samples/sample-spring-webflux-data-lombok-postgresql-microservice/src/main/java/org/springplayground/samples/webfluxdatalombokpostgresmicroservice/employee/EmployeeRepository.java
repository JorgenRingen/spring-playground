package org.springplayground.samples.webfluxdatalombokpostgresmicroservice.employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

