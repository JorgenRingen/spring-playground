package org.springplayground.samples.mvcdatalombokpostgresmicroservice.company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
