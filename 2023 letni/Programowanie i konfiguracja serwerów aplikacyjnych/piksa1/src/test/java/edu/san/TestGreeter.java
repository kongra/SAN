// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestGreeter {

  Greeter greeter;

  @BeforeEach
  void setUp() {
    greeter = new Greeter("Hello");
  }

  @Test
  void testGetGreetingText() {
    final var s1 = greeter.getGreetingText();
    assertThat(s1).isNotNull().isNotBlank().isEqualTo("Hello");
  }

  @Test
  void testGreetMe() {
    final var s1 = greeter.greetMe("Frank");
    assertThat(s1).isNotNull().isNotBlank().isEqualTo("Hello Frank");
  }

}
