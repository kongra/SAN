package eshop.profile;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="EMPLOYEES")
public class Employee {

  @EmbeddedId
  private EmployeeKey id;

  private String login;
  
  private String passwd;
  
}
