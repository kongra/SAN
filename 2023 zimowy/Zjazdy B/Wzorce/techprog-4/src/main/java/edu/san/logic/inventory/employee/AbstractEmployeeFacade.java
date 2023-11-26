package edu.san.logic.inventory.employee;

import java.util.Optional;

import edu.san.logic.inventory.company.CompanyFacade;
import edu.san.logic.inventory.company.Email;

public interface AbstractEmployeeFacade {

  Employee createEmployee(EmployeeId employeeId,
      FirstName firstName,
      LastName lastName,
      Email email,
      Department department);

  default Employee enrolEmployee(CompanyFacade companyFacade,
      FirstName firstName,
      LastName lastName,
      Department department) {
    final var employeeId = createEmployeeId();
    return createEmployee(employeeId,
        firstName, lastName,
        companyFacade.createEmail(employeeId, firstName, lastName), department);
  }

  EmployeeId createEmployeeId();

  Optional<FirstName> asFirstName(String s);

  Optional<LastName> asLastName(String s);

  Optional<Email> asEmail(String s);

  Optional<Department> asDepartment(String s);

}
