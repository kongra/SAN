package ewus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "EMPLOYEES")
public class Employee implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_ID_GEN")
  @SequenceGenerator(name = "EMPLOYEE_ID_GEN", sequenceName = "EMPLOYEE_ID_SEQ")
  // @GeneratedValue(strategy = GenerationType.TABLE, generator =
  // "EMPLOYEE_ID_GEN")
  // @TableGenerator(name = "EMPLOYEE_ID_GEN", table = "SEQUENCE_TABLE",
  // pkColumnName = "SEQ_NAME", pkColumnValue = "EMPLOYEE_ID_SEQ")
  private long id;

  private String firstName;

  private String lastName;

  private BigDecimal salary;

  @ManyToOne
  private Employee manager;

  @OneToMany
  private List<Employee> managedEmployees;

  public long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  public Employee getManager() {
    return manager;
  }

  public void setManager(Employee manager) {
    this.manager = manager;
  }

  public List<Employee> getManagedEmployees() {
    return managedEmployees;
  }

  public void setManagedEmployees(List<Employee> managedEmployees) {
    this.managedEmployees = managedEmployees;
  }

  private static final long serialVersionUID = 1L;

}
