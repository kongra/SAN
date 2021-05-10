package edu.san.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("static-method")
class SampleTest {

  @Test
  void test() {
    assertThat(1 + 2 <= 3).isTrue();
  }

}
