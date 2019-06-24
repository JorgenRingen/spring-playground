package org.springplayground.samples.mvcdatalombokpostgresmicroservice.employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

