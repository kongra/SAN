// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.patterns.equivalence;

import java.util.Objects;

public class Point2D {

  public final double x;

  public final double y;

  public Point2D(double x, double y) {
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
    if (!(obj instanceof final Point2D other))
      return false;
    return Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
        && Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
  }
}
