package edu.san.logic.inventory.company;

import edu.san.logic.inventory.employee.EmployeeId;
import edu.san.logic.inventory.employee.FirstName;
import edu.san.logic.inventory.employee.LastName;

public interface CompanyFacade {

  Email createEmail(EmployeeId employeeId, FirstName firstName,
      LastName lastName);

}
