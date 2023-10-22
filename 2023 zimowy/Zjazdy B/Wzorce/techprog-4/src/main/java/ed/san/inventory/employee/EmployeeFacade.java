package ed.san.inventory.employee;

import ed.san.inventory.company.CompanyFacade;
import ed.san.inventory.company.Email;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public abstract class EmployeeFacade {

  protected abstract Employee createEmployee(EmployeeId employeeId,
                                             FirstName firstName,
                                             LastName lastName,
                                             Email email,
                                             Department department);

  public Employee enrolEmployee(@NotNull CompanyFacade companyFacade,
                                FirstName firstName,
                                LastName lastName,
                                Department department) {
    final var employeeId = createEmployeeId();
    return createEmployee(employeeId,
        firstName, lastName, companyFacade.createEmail(employeeId, firstName, lastName), department);
  }


  public abstract EmployeeId createEmployeeId();

  public abstract Optional<FirstName> asFirstName(String s);

  public abstract Optional<LastName> asLastName(String s);

  public abstract Optional<Email> asEmail(String s);

  public abstract Optional<Department> asDepartment(String s);

}
