package org.springplayground.samples.webfluxdatalombokpostgresmicroservice.company;

import reactor.core.publisher.Mono;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springplayground.samples.webfluxdatalombokpostgresmicroservice.employee.Employee;
import org.springplayground.samples.webfluxdatalombokpostgresmicroservice.employee.EmployeeNotFoundException;
import org.springplayground.samples.webfluxdatalombokpostgresmicroservice.employee.EmployeeService;

@Service
@Transactional
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final EmployeeService employeeService;

    public CompanyService(CompanyRepository companyRepository,
                          EmployeeService employeeService) {
        this.companyRepository = companyRepository;
        this.employeeService = employeeService;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Optional<Company> findById(long id) {
        return companyRepository.findById(id);
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public void delete(long id) {
        companyRepository.findById(id)
                .ifPresent(companyRepository::delete);
    }

    public void addEmployee(long companyId, Mono<Long> employeeIdMono) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(CompanyNotFoundException::new);
        long employeeId = employeeIdMono.toProcessor().block(); // needs to block
        if (company.isEmployeeEmployed(employeeId)) {
            throw new EmployeeAlreadyEmployedInCompanyException(companyId, employeeId);
        }

        Employee employee = employeeService.findById(employeeId)
                .orElseThrow(EmployeeNotFoundException::new);

        company.addEmployee(employee);
    }

    public void removeEmployee(long companyId, long employeeId) {
        companyRepository.findById(companyId)
                .ifPresent(company -> company.removeEmployee(employeeId));
    }
}
