package san.pp;

public class TestPoint {

  public static void main(String[] args) {
    Point p1 = new Point(1, 2);
    Point p2 = new Point();

    System.out.println(Point.repr(p1));
    System.out.println(Point.repr(p2));

    // p1.x = 3;
    // p1.y = 4;
  }

}
