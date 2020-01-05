package san.pp;

public class TestPoint {

  public static void main(String[] args) {
    Point p1 = new Point(1, 2);
    Point p2 = new Point();

    System.out.println(p1.repr());
    System.out.println(p2.repr());

    // p1.x = 3;
    // p1.y = 4;
  }

}
