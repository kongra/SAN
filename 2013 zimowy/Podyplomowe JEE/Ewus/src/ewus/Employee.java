package ewus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// @DiscriminatorColumn(name="EMP_TYPE")
// @DiscriminatorValue("E")
@Table(name = "EMPLOYEES")
@Access(AccessType.FIELD)
public class Employee implements Serializable {

  // @Id
  // @GeneratedValue(strategy = GenerationType.TABLE, generator =
  // "EMPLOYEE_ID_GEN")
  // @TableGenerator(name = "EMPLOYEE_ID_GEN", table = "SEQUENCE_TABLE",
  // pkColumnName = "SEQ_NAME", pkColumnValue = "EMPLOYEE_ID_SEQ")
  // private long id;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_ID_GEN")
  @SequenceGenerator(name = "EMPLOYEE_ID_GEN", sequenceName = "EMPLOYEE_ID_SEQ")
  private long id;

  @Version
  private long version;

  private String firstName;

  private String lastName;

  @Transient
  private Money salary;

  @ManyToOne(fetch = FetchType.LAZY)
  private Manager manager;

  @OneToOne(fetch = FetchType.LAZY, cascade = {
      CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE
  })
  private Address address;

  // private | official | fax → Phone
  @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
  private Map<String, Phone> phones;

  public long getId() {
    return id;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
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

  public Manager getManager() {
    return manager;
  }

  protected void setManager(Manager manager) {
    this.manager = manager;
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

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Map<String, Phone> getPhones() {
    return phones;
  }

  public void setPhones(Map<String, Phone> phones) {
    this.phones = phones;
  }

  private static final long serialVersionUID = 1L;

}
