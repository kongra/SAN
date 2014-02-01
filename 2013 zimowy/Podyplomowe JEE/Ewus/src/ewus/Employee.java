package ewus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "EMPLOYEES")
@Access(AccessType.FIELD)
@IdClass(EmpID.class)
public class Employee implements Serializable {

  // @Id
  // @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
  // "EMPLOYEE_ID_GEN")
  // @SequenceGenerator(name = "EMPLOYEE_ID_GEN", sequenceName =
  // "EMPLOYEE_ID_SEQ")
  // @GeneratedValue(strategy = GenerationType.TABLE, generator =
  // "EMPLOYEE_ID_GEN")
  // @TableGenerator(name = "EMPLOYEE_ID_GEN", table = "SEQUENCE_TABLE",
  // pkColumnName = "SEQ_NAME", pkColumnValue = "EMPLOYEE_ID_SEQ")
  // private long id;

  @Id
  private long deptId;

  @Id
  private long id;

  private String firstName;

  private String lastName;

  @Transient
  private Money salary;

  @ManyToOne
  private Employee manager;

  @OneToMany
  private List<Employee> managedEmployees;

  public long getDeptId() {
    return deptId;
  }

  public void setDeptId(long deptId) {
    this.deptId = deptId;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public Employee getManager() {
    return manager;
  }

  private void setManager(Employee manager) {
    this.manager = manager;
  }

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

  @Access(AccessType.PROPERTY)
  private BigDecimal getSalaryValue() {
    return salary != null ? salary.value : null;
  }

  private void setSalaryValue(BigDecimal salaryValue) {
    this.salary = new Money(salaryValue, getSalaryCurrency());
  }

  @Access(AccessType.PROPERTY)
  @Basic
  @Enumerated(EnumType.STRING)
  private Currency getSalaryCurrency() {
    return salary != null ? salary.currency : null;
  }

  private void setSalaryCurrency(Currency salaryCurrency) {
    this.salary = new Money(getSalaryValue(), salaryCurrency);
  }

  public Money getSalary() {
    return salary;
  }

  public void setSalary(Money salary) {
    this.salary = salary;
  }

  private static final long serialVersionUID = 1L;

}
