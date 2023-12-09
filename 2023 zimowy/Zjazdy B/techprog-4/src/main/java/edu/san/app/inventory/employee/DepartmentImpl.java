// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.app.inventory.employee;

import edu.san.logic.inventory.company.Department;

record DepartmentImpl(String name) implements Department {

  @Override
  public String asString() {
    return name();
  }

}