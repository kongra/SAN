// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.functional;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeronTest {

  static final MathContext mathContext = new MathContext(100,
      RoundingMode.HALF_UP);

  Heron<BigDecimal> bigHeron;

  Heron<Double> doubleHeron;

  @BeforeEach
  void setUp() {
    bigHeron = new Heron<>(
        BigDecimal::add,
        BigDecimal::subtract,
        (x, y) -> x.multiply(y, mathContext),
        (x, y) -> x.divide(y, mathContext),
        BigDecimal::negate,
        (x, y) -> x.compareTo(y) < 0,
        BigDecimal.ZERO,
        BigDecimal.ONE,
        BigDecimal.valueOf(2L),
        BigDecimal.valueOf(0.0000000001d));

    doubleHeron = new Heron<>(
        (x, y) -> x + y,
        (x, y) -> x - y,
        (x, y) -> x * y,
        (x, y) -> x / y,
        x -> -x,
        (x, y) -> x < y,
        0d,
        1d,
        2d,
        0.0000000001d);
  }

  @Test
  void testSqrt() {
    System.out.println(
        bigHeron.sqrt(BigDecimal.valueOf(2L)));

    System.out.println(
        doubleHeron.sqrt(2d));

    final var sqrt1 = bigHeron.sqrt(BigDecimal.valueOf(2L));
    final var square1 = sqrt1.multiply(sqrt1, mathContext);
    final var two = BigDecimal.valueOf(2L);

    assertThat(square1).isEqualByComparingTo(two);
  }

}
