// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.equivalence;

import java.util.Objects;

public class ColoredPoint2D extends Point2D {

  private final Color color;

  public ColoredPoint2D(double x, double y, Color color) {
    super(x, y);
    this.color = color;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, color);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (!(obj instanceof ColoredPoint2D)) {
      return false;
    }
    ColoredPoint2D other = (ColoredPoint2D) obj;
    return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
        && Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y)
        && color == other.color;
  }

}
