package org.springplayground.samples.springhateoas.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orderline {

    @Id
    @GeneratedValue
    private Long id;
    private String productId;
    private Integer quantity;
}