public class Program1 {

  public static void main(String[] args) {
    var p1 = new Point(1, 2); // Point.make(1, 2);
    var p2 = new Point(3, 4); // Point.make(3, 4);
    var p3 = p1.add(p2);      // Point.add(p1, p2);

    // var p3 = p1 + p2;
    // var p3 = p1 `add` p2;
    var p3 = p1.add(p2);

    var p4 = new Point(11, 2); // Point.make(11, 12);
    var p5 = p4.add(p3);

    var p6 = new Point(7, 7);
    var p7 = p5.add(p6);

    System.out.println(p1.asString());
    System.out.println(p2.asString());
    System.out.println(p3.asString());
    System.out.println(p5.asString());
    System.out.println(p7.asString());
  }

}
