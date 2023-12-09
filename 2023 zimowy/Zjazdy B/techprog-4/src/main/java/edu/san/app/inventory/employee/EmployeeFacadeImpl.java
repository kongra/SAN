// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.app.inventory.employee;

import java.util.Optional;
import java.util.UUID;

import edu.san.logic.inventory.company.Department;
import edu.san.logic.inventory.company.Email;
import edu.san.logic.inventory.employee.Employee;
import edu.san.logic.inventory.employee.EmployeeFacade;
import edu.san.logic.inventory.employee.EmployeeId;
import edu.san.logic.inventory.employee.FirstName;
import edu.san.logic.inventory.employee.LastName;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
class EmployeeFacadeImpl implements EmployeeFacade {

  private final EmployeeEntityRepository employeeEntityRepository;

  EmployeeFacadeImpl(EmployeeEntityRepository employeeEntityRepository) {
    this.employeeEntityRepository = employeeEntityRepository;
  }

  @Transactional
  @Override
  public Employee createEmployee(EmployeeId employeeId, FirstName firstName,
      LastName lastName, Email email, Department department) {
    final var employeeEntity = new EmployeeEntity(firstName.asString(),
        lastName.asString(), email.asString(), department.asString());
    employeeEntityRepository.persist(employeeEntity);
    return employeeEntity;
  }

  @Override
  public EmployeeId createEmployeeId() {
    return new EmployeeIdImpl(UUID.randomUUID());
  }

  @Override
  public Optional<FirstName> validateFirstName(String s) {
    return s == null || s.isBlank() ? Optional.empty()
        : Optional.of(new FirstNameImpl(s));
  }

  @Override
  public Optional<LastName> validateLastName(String s) {
    return s == null || s.isBlank() ? Optional.empty()
        : Optional.of(new LastNameImpl(s));
  }

}
