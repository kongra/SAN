// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("static-method")
class StringsTest {

  @Test
  void test() {
    String someString = "xyz";
    assertThat(someString).isNotBlank();
  }

}
