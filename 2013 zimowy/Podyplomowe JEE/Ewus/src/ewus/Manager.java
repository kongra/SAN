package ewus;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("M")
// @Table(name = "MANAGERS")
public class Manager extends Employee {

  @OneToMany
  private List<Employee> managedEmployees;
  
  public List<Employee> getManagedEmployees() {
    return managedEmployees;
  }

  public void setManagedEmployees(List<Employee> managedEmployees) {
    this.managedEmployees = managedEmployees;
    for (Employee employee : managedEmployees) {
      if (!this.equals(employee.getManager())) {
        employee.setManager(this);
      }
    }
  }
  
}
