package san.lect03;

import san.math.Point2D;

public class TestHashCode {

  public static void main(String[] args) {
    Point2D a = new Point2D(1, 2);
    Point2D b = new Point2D(1, 2);

    System.out.println(a.equals(b));
    System.out.println(a.hashCode());
    System.out.println(b.hashCode());

    System.out.println(b.hashCode());
  }

}
