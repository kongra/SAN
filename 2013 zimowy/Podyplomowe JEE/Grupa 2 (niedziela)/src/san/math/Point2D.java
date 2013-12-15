package san.math;

public class Point2D {

  public final double x;

  public final double y;

  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(x);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    temp = Double.doubleToLongBits(y);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Point2D)) {
      return false;
    }
    Point2D other = (Point2D) obj;
    if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
      return false;
    }
    if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
      return false;
    }
    return true;
  }

  public double distanceFromOrigin() {
    return Math.sqrt(this.x * this.x + this.y * this.y);
  }

  @Override
  public String toString() {
    return "Point2D [x=" + x + ", y=" + y + "]";
  }

}
