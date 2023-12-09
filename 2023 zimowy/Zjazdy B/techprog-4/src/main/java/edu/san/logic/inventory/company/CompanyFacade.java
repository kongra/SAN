package edu.san.logic.inventory.company;

import edu.san.logic.inventory.employee.EmployeeId;
import edu.san.logic.inventory.employee.FirstName;
import edu.san.logic.inventory.employee.LastName;

public interface CompanyFacade {

  default Email createEmail(EmployeeId employeeId, FirstName firstName,
      LastName lastName) {
    final var emailValue = employeeId.asEmailPrefix() + "_" + firstName + "_"
        + lastName + "@inventory.com";
    return persistEmail(emailValue);
  }

  Email persistEmail(String emailValue);

}
