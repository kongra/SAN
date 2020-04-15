package san.jipp.identity;

public class ColoredPoint2D extends Point2D {

  private final Color color;

  public ColoredPoint2D(double x, double y, Color color) {
    super(x, y);
    this.color = color;
  }

// @Override
//  public boolean equals(Object o) {
//    if (this == o) return true;
//    if (!(o instanceof ColoredPoint2D)) return false;
//    if (!super.equals(o)) return false;
//
//    ColoredPoint2D that = (ColoredPoint2D) o;
//    return color == that.color;
//  }
//  @Override
//  public int hashCode() {
//    int result = super.hashCode();
//    result = 31 * result + color.hashCode();
//    return result;
//  }
}
