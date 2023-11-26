package edu.san.logic.inventory.employee;

import java.util.Optional;

import edu.san.logic.inventory.company.CompanyFacade;
import edu.san.logic.inventory.company.Email;

public abstract class AbstractEmployeeFacade {

  protected abstract Employee createEmployee(EmployeeId employeeId,
      FirstName firstName,
      LastName lastName,
      Email email,
      Department department);

  public Employee enrolEmployee(CompanyFacade companyFacade,
      FirstName firstName,
      LastName lastName,
      Department department) {
    final var employeeId = createEmployeeId();
    return createEmployee(employeeId,
        firstName, lastName,
        companyFacade.createEmail(employeeId, firstName, lastName), department);
  }

  public abstract EmployeeId createEmployeeId();

  public abstract Optional<FirstName> asFirstName(String s);

  public abstract Optional<LastName> asLastName(String s);

  public abstract Optional<Email> asEmail(String s);

  public abstract Optional<Department> asDepartment(String s);

}
