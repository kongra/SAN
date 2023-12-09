// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.app.inventory.employee;

import edu.san.logic.inventory.employee.FirstName;

record FirstNameImpl(String value) implements FirstName {

  @Override
  public String asString() {
    return value();
  }

}
