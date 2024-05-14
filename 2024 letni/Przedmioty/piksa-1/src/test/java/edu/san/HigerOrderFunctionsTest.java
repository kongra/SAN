// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HigerOrderFunctionsTest {

  @BeforeEach
  void setUp() throws Exception {}

  @FunctionalInterface
  interface Term {
    double call(double x);
  }

  @FunctionalInterface
  interface Next {
    int call(int n);
  }

  static double sum(int a, int b, double result, Term term, Next next) {
    if (a > b)
      return result;

    return sum(next.call(a), b, result + term.call(a), term, next);
  }

  static double sum1(int a, int b, double result, Term term, Next next) {
    return a > b ? result
        : sum(next.call(a), b, result + term.call(a), term, next);
  }

  static double sum2(int a, int b, double result, Term term, Next next) {
    for (;;) {
      if (a > b)
        return result;

      result += term.call(a);
      a       = next.call(a);
    }
  }

  static double sumInts(int a, int b) {
    return sum2(a, b, 0, x -> x, x -> x + 1);
  }

  @SuppressWarnings("static-method")
  @Test
  void testSumInts() {
    final var x = sumInts(0, 10);
    assertThat(x).isEqualTo(55.0);
  }

}
