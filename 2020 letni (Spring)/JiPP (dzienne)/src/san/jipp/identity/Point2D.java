package san.jipp.identity;

public class Point2D {

  private final double x;

  private final double y;

  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public String toString() {
    return "Point2D{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Point2D)) return false;
    Point2D point2D = (Point2D) o;
    return Double.compare(point2D.x, x) == 0 &&
           Double.compare(point2D.y, y) == 0;
  }

  @Override
  public final int hashCode() {
    int result;
    long temp;
    temp = Double.doubleToLongBits(x);
    result = (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(y);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }
}
