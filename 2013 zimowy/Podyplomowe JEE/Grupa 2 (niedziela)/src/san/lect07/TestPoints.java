package san.lect07;

public class TestPoints {

  public static void main(String[] args) {
    // test1();

    Point p1 = new Point(1, 2);
    Point3D p2 = new Point3D(1, 2, 3);
    
    System.out.println(p1.equals(p2));
    System.out.println(p2.equals(p1));
  }

  private static void test1() {
    Point p1 = new Point(1, 2);
    Point p2 = new Point(3, 4);
    Point p3 = p1;
    Point p4 = new Point(1, 2);
    
    System.out.println(p1.equals(p2)); // false
    System.out.println(p1.equals(p3)); // true
    System.out.println(p1.equals(p4)); // true
    
    System.out.println(p1.hashCode());
    System.out.println(p2.hashCode());
    System.out.println(p3.hashCode());
    System.out.println(p4.hashCode());
  }

}
