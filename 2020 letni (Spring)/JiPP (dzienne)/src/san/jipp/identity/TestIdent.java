package san.jipp.identity;

public class TestIdent {

  public static void main(String... args) {
    Point2D p1 = new Point2D(1, 2);
    ColoredPoint2D p2 = new ColoredPoint2D(1, 2, Color.RED);
    System.out.println(p1.equals(p2));
    System.out.println(p2.equals(p1));

    // testEquiv();
  }

  private static void testEquiv() {
    Point2D p1 = new Point2D(1, 2);
    Point2D p2 = new Point2D(3, 4);
    Point2D p3 = new Point2D(1, 2);
    Point2D p4 = p1;

    System.out.println(p1.equals(p2));
    System.out.println(p2.equals(p3));
    System.out.println(p1.equals(p3));
    System.out.println(p1.equals(p4));
  }

}
