package org.springplayground.samples.contenttypenegotiation.resources.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyMinimal {

    private String name;

    public static CompanyMinimal from(Company company) {
        return CompanyMinimal.builder()
                .name(company.name)
                .build();
    }
}
