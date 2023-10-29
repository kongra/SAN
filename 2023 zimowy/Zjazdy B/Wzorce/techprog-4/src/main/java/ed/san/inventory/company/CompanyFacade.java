package ed.san.inventory.company;

import ed.san.inventory.employee.EmployeeId;
import ed.san.inventory.employee.FirstName;
import ed.san.inventory.employee.LastName;

public interface CompanyFacade {

  Email createEmail(EmployeeId employeeId, FirstName firstName,
      LastName lastName);

}
