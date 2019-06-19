package org.springplayground.samples.contenttypenegotiation.resources;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springplayground.samples.contenttypenegotiation.resources.model.Company;
import org.springplayground.samples.contenttypenegotiation.resources.model.CompanyMinimal;

@RestController
@RequestMapping("companies")
public class CompanyResource {

    private static final List<Company> COMPANIES = Arrays.asList(
            Company.builder()
                    .name("Accenture")
                    .founded(LocalDate.of(2001, Month.JANUARY, 1))
                    .build(),
            Company.builder()
                    .name("Facebook")
                    .founded(LocalDate.of(2008, Month.FEBRUARY, 4))
                    .build());

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, "application/vnd.companies.api.v1.company+json"})
    public List<Company> list() {
        return COMPANIES;
    }

    @GetMapping(produces = {"application/vnd.companies.api.v1.company-minimal+json"})
    public List<CompanyMinimal> listMinimal() {
        return COMPANIES.stream()
                .map(CompanyMinimal::from)
                .collect(Collectors.toList());
    }

}
