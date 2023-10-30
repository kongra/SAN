package ed.san.inventory.employee;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class EmployeeFacadeTest {

  AbstractEmployeeFacade employeeFacade;

  @Test
  void testEnrolEmployee() {
    assertThat(employeeFacade).isNotNull();

//    final var firstName = employeeFacade
//        .asFirstName("Krystyna")
//        .orElseThrow(IllegalArgumentException::new);
//
//    final var lastName = employeeFacade
//        .asLastName("Nowak")
//        .orElseThrow(IllegalArgumentException::new);

  }
}
