package org.springplayground.samples.mvcdatalombokpostgresmicroservice.company;

class EmployeeAlreadyEmployedInCompanyException extends RuntimeException {

    EmployeeAlreadyEmployedInCompanyException(long companyId, long employeeId) {
        super("Employee with id=" + employeeId + " already employed in company with id=" + companyId);
    }
}
