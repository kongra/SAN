package multi;

import org.jetbrains.annotations.Contract;

import java.util.Objects;

public class Point {

  public final double x;

  public final double y;

  @Contract(pure = true)
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Point)) return false;
    Point point = (Point) o;
    return Double.compare(point.x, x) == 0 &&
        Double.compare(point.y, y) == 0;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(x, y);
  }

  public static void main(String... args) {
    Point p1 = new Point(1, 1);
    Point p2 = new Point(2, 2);
    Point p3 = new Point(1, 1);

    System.out.println(p1.equals(p2));
    System.out.println(p1.equals(p3));
  }
}
