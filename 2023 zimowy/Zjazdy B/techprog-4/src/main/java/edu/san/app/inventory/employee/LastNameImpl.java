// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.app.inventory.employee;

import edu.san.logic.inventory.employee.LastName;

record LastNameImpl(String value) implements LastName {

  @Override
  public String asString() {
    return value();
  }

}