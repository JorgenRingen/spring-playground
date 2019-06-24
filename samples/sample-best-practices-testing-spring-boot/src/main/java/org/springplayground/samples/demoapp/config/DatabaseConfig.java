package org.springplayground.samples.demoapp.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springplayground.samples.demoapp.domain.Order;
import org.springplayground.samples.demoapp.repository.OrderRespository;

@Configuration
@EnableJpaRepositories(basePackageClasses = {OrderRespository.class})
@EntityScan(basePackageClasses = {Order.class})
public class DatabaseConfig {

}
