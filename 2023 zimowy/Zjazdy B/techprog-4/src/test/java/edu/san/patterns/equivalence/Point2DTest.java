// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.equivalence;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class Point2DTest {

  @SuppressWarnings("static-method")
  @Test
  void testEquivalence() {
    final var x = new Point2D(1, 2);
    final var x1 = new Point2D(1, 2);
    final var x2 = new Point2D(1, 2);
    final var y = new Point2D(3, 4);

    // 1. Reflective
    assertThat(x).isEqualTo(x)

        // Overall semantics
        .isEqualTo(x1).isNotEqualTo(y);

    // 2. Symmetric
    assertThat(x).isEqualTo(x1);
    assertThat(x1).isEqualTo(x);

    // 3. Transitive
    assertThat(x).isEqualTo(x);
    assertThat(x1).isEqualTo(x2);
    assertThat(x).isEqualTo(x2);

    // 4. Consistent:
    assertThat(x).isEqualTo(x1);
    assertThat(x).isNotEqualTo(y);
    // x.y = 1000;
    assertThat(x).isEqualTo(x1);
    assertThat(x).isNotEqualTo(y);

    // 5. not equal to null
    assertThat(x.equals(null)).isFalse();

  }

  @SuppressWarnings("static-method")
  @Test
  void testColoredEquivalence() {
    final var x = new ColoredPoint2D(1, 2, Color.RED);
    final var x1 = new ColoredPoint2D(1, 2, Color.RED);
    final var x2 = new ColoredPoint2D(1, 2, Color.RED);
    final var y = new ColoredPoint2D(3, 4, Color.GREEN);

    // 1. Reflective
    assertThat(x).isEqualTo(x)

        // Overall semantics
        .isEqualTo(x1).isNotEqualTo(y);

    // 2. Symmetric
    assertThat(x).isEqualTo(x1);
    assertThat(x1).isEqualTo(x);

    // 3. Transitive
    assertThat(x).isEqualTo(x);
    assertThat(x1).isEqualTo(x2);
    assertThat(x).isEqualTo(x2);

    // 4. Consistent:
    assertThat(x).isEqualTo(x1);
    assertThat(x).isNotEqualTo(y);
    // x.y = 1000;
    assertThat(x).isEqualTo(x1);
    assertThat(x).isNotEqualTo(y);

    // 5. not equal to null
    assertThat(x.equals(null)).isFalse();

  }

  @SuppressWarnings("static-method")
  @Test
  void testColoredEquivalence1() {
    final var x = new Point2D(1, 2);
    final var y = new ColoredPoint2D(1, 2, Color.RED);

    assertThat(x.equals(y)).isTrue();
    assertThat(y.equals(x)).isTrue(); // Volated symmetry!!!

  }

}
