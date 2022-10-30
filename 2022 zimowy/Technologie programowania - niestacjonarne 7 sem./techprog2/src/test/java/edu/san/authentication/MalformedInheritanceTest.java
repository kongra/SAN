package edu.san.authentication;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Objects;

import org.junit.jupiter.api.Test;

class MalformedInheritanceTest {

  enum Color {
    BLACK, RED, GREEN
  }

  static class ColoredPoint extends Point {

    final Color color;

    ColoredPoint(double x, double y, Color color) {
      super(x, y);
      this.color = color;
    }

//    @Override
//    public int hashCode() {
//      final var prime = 31;
//      final var result = super.hashCode();
//      return prime * result + (color == null ? 0 : color.hashCode());
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//      if (this == obj)
//        return true;
//      if (!super.equals(obj) || !(obj instanceof final ColoredPoint other)
//          || color != other.color)
//        return false;
//      return true;
//    }

  }

  static class Point {

    final double x;

    final double y;

    Point(double x, double y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public final int hashCode() {
      return Objects.hash(x, y);
    }

    @Override
    public final boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (!(obj instanceof final Point other)
          || Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)
          || Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
        return false;
      return true;
    }

  }

//  The equals method implements an equivalence relation. It has these properties:
//    • Reflexive: For any non-null reference value x , x.equals(x) must return true .
//    • Symmetric: For any non-null reference values x and y , x.equals(y) must re-
//    turn true if and only if y.equals(x) returns true .
//    • Transitive: For any non-null reference values x , y , z , if x.equals(y) returns
//    true and y.equals(z) returns true , then x.equals(z) must return true.
//    • Consistent: For any non-null reference values x and y , multiple invocations
//    of x.equals(y) must consistently return true or consistently return false ,
//    provided no information used in equals comparisons is modified.
//    • For any non-null reference value x , x.equals(null) must return false.

// https://www.sonarsource.com/knowledge/languages/java/

  @Test
  void testPoints() {
    final var a = new Point(1, 2);
    final var b = new Point(1, 2);

    // Reflexive:
    assertThat(a).isEqualTo(a);

    // Symmetric:
    assertThat(a).isEqualTo(b);
    assertThat(b).isEqualTo(a);

    // Transitive:
    final var c = new Point(1, 2);
    assertThat(b).isEqualTo(c);
    assertThat(a).isEqualTo(c);

    assertThat(a).isNotEqualTo(null);

  }

  @Test
  void testColoredPoints() {
    final var a = new ColoredPoint(1, 2, Color.BLACK);
    final var b = new ColoredPoint(1, 2, Color.BLACK);

    // Reflexive:
    assertThat(a).isEqualTo(a);

    // Symmetric:
    assertThat(a).isEqualTo(b);
    assertThat(b).isEqualTo(a);
    
    final var c = new ColoredPoint(1, 2, Color.RED);
    assertThat(a).isEqualTo(c);
    assertThat(b).isEqualTo(c);

  }
  
  @Test
  void testMixedPoints() {
    final var a = new Point(1, 2);
    final var b = new ColoredPoint(1, 2, Color.BLACK);

    // Reflexive:
    assertThat(a).isEqualTo(a);

    // Symmetric:
    assertThat(a).isEqualTo(b);
    assertThat(b).isEqualTo(a);
  }

}
