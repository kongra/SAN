import java.util.*;

class Program {

  public static void main(String... args) {
    List<Point> l1 = new ArrayList<>();
    l1.add(new Point(1, 2));
    l1.add(new Point(3, 4));

    Sender.send(l1);

    List<Integer> l2 = new ArrayList<>();
    l2.add(5);
    l2.add(6);
    Sender.send(l2);
  }

}
