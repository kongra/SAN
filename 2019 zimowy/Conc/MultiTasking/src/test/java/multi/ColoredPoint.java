// Copyright (c) Konrad Grzanek
// Created 16.11.2019
package multi;

import java.util.Objects;

public class ColoredPoint extends Point {

  public final String color;

  public ColoredPoint(double x, double y, String color) {
    super(x, y);
    this.color = color;
  }

//  @Override
//  public boolean equals(Object o) {
//    if (this == o) return true;
//    if (!(o instanceof ColoredPoint)) return false;
//    if (!super.equals(o)) return false;
//    ColoredPoint that = (ColoredPoint) o;
//    return color.equals(that.color);
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(super.hashCode(), color);
//  }

  public static void main(String... args) {
    Point p1 = new ColoredPoint(1, 1, "red");
    Point p2 = new ColoredPoint(2, 2, "green");
    Point p3 = new ColoredPoint(1, 1, "yellow");
    Point p4 = new ColoredPoint(1, 1, "red");

//    System.out.println(p1.equals(p2));
//    System.out.println(p1.equals(p3));
//    System.out.println(p1.equals(p4));

    Point p5 = new Point(1, 1);
    System.out.println(p1.equals(p5));
    System.out.println(p5.equals(p1));
  }
}
