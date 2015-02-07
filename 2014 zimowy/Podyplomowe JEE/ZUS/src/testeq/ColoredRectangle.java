package testeq;

public class ColoredRectangle extends Rectangle {

  private final Color color;

  public ColoredRectangle(double a, double h, double x, double y, Color color) {
    super(a, h, x, y);
    this.color = color;
  }

}
