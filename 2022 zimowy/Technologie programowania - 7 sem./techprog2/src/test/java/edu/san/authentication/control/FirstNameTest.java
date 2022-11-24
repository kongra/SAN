// © 2022 Konrad Grzanek <kongra@gmail.com>
package edu.san.authentication.control;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("static-method")
class FirstNameTest {

  @Test
  void testIsValid() {
    assertThat(FirstName.isValid("John")).isTrue();
    assertThat(FirstName.isValid("John Doe")).isFalse();
    assertThat(FirstName.isValid("Some1")).isFalse();
    assertThat(FirstName.isValid(" John")).isFalse();

    assertThat(FirstName.isValid(
        "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
        .isTrue();
    assertThat(FirstName.isValid(
        "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"))
        .isFalse();

    assertThat(FirstName.isValid("john")).isFalse();

    assertThat(FirstName.isValid(null)).isFalse();
  }

}
