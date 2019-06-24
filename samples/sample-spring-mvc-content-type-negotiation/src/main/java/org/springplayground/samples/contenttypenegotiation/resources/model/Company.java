package org.springplayground.samples.contenttypenegotiation.resources.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Company {

    public String name;
    public LocalDate founded;

}
