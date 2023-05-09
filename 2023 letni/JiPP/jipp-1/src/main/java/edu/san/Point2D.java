package edu.san;

public class Point2D {

  private final double x;

  private final double y;

  public Point2D(double x, double y) {
    this.x = x; // self
    this.y = y;
  }

  public double dist() {
    return Math.sqrt(x * x + y * y);
  }

}
