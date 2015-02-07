package testeq;

public class Rectangle {

  private final double a;

  private final double h;

  private final double x;

  private final double y;

  public Rectangle(double a, double h, double x, double y) {
    super();
    this.a = a;
    this.h = h;
    this.x = x;
    this.y = y;
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Rectangle)) {
      return false;
    }
    Rectangle other = (Rectangle) obj;
    if (Double.doubleToLongBits(a) != Double.doubleToLongBits(other.a)) {
      return false;
    }
    if (Double.doubleToLongBits(h) != Double.doubleToLongBits(other.h)) {
      return false;
    }
    if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x)) {
      return false;
    }
    if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y)) {
      return false;
    }
    return true;
  }

  
  
}
