// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.app.inventory.employee;

import java.util.UUID;

import edu.san.app.jpa.IdBasedIdentity;
import edu.san.logic.inventory.employee.Employee;
import edu.san.logic.inventory.employee.EmployeeId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;

@Entity
class EmployeeEntity
    implements Employee, IdBasedIdentity<EmployeeEntity, UUID> {

  @Id
  private UUID id = UUID.randomUUID();

  @Version
  private short version;

  private String firstName;

  private String lastName;

  private String email;

  private String department;

  EmployeeEntity() {}

  EmployeeEntity(String firstName, String lastName, String email,
      String department) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.department = department;
  }

  @Override
  public EmployeeId getEmployeeId() {
    return new EmployeeIdImpl(id);
  }

  @Override
  public UUID getId() {
    return id;
  }

  void setId(UUID id) {
    this.id = id;
  }

  short getVersion() {
    return version;
  }

  void setVersion(short version) {
    this.version = version;
  }

  String getFirstName() {
    return firstName;
  }

  void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  String getLastName() {
    return lastName;
  }

  void setLastName(String lastName) {
    this.lastName = lastName;
  }

  String getEmail() {
    return email;
  }

  void setEmail(String email) {
    this.email = email;
  }

  String getDepartment() {
    return department;
  }

  void setDepartment(String department) {
    this.department = department;
  }

  @Override
  public final int hashCode() {
    return hash();
  }

  @Override
  public final boolean equals(Object obj) {
    return isEqual(obj);
  }

}
