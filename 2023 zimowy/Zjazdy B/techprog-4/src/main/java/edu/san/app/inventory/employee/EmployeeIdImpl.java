// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.app.inventory.employee;

import java.util.Objects;
import java.util.UUID;

import edu.san.logic.inventory.employee.EmployeeId;

record EmployeeIdImpl(UUID uuid) implements EmployeeId {

  EmployeeIdImpl {
    Objects.requireNonNull(uuid);
  }

  @Override
  public String asEmailPrefix() {
    return uuid.toString();
  }

}
