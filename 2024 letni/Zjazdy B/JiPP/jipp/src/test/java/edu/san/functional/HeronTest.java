// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.functional;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeronTest {

  Heron<BigDecimal> bigHeron;

  static final MathContext mc = new MathContext(100, RoundingMode.HALF_UP);

  Heron<Double> doubleHeron;

  @BeforeEach
  void setUp() {
    bigHeron = new Heron<BigDecimal>(
        BigDecimal::add,
        BigDecimal::subtract,
        (x, y) -> x.multiply(y, mc),
        (x, y) -> x.divide(y, mc),
        BigDecimal::negate,
        (x, y) -> x.compareTo(y) < 0,
        BigDecimal.ZERO,
        BigDecimal.ONE,
        BigDecimal.valueOf(2),
        BigDecimal.valueOf(0.0000000001));

    doubleHeron = new Heron<Double>(
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
  }

}
