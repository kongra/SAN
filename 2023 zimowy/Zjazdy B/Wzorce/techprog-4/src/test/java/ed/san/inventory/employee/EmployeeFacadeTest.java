package ed.san.inventory.employee;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeFacadeTest {

  EmployeeFacade employeeFacade;

  @Test
  void testEnrolEmployee() {
    final var firstName = employeeFacade
        .asFirstName("Krystyna")
        .orElseThrow(IllegalArgumentException::new);

    final var lastName = employeeFacade
        .asLastName("Nowak")
        .orElseThrow(IllegalArgumentException::new);

  }
}
