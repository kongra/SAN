package edu.san.factories;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Program1 {

  static <E> void dump(List<E> lst) {
    for (E e : lst) {
      System.out.println(e);
    }
  }

  static final boolean USE_LINKED_LIST = "true"
      .equals(System.getProperty("use-linked-list"));

  // java -Duse-linked-list=true Program1

  // Najlepiej w klasie Utils:
  static <E> List<E> newList() { // FACTORY METHOD
    return USE_LINKED_LIST ? new LinkedList<>() : new ArrayList<>();
  }

  public static void main(String... args) {
    var l1 = newList();
    var l2 = newList();
    var l3 = newList();

    List<String> strings1 = newList();
    // x 10_000

    dump(l1);
    dump(l2);
    dump(l3);
    dump(strings1);

    var p1 = Point.of(1, 2);
    var p2 = Point.origin(); // Point.of(0, 0);
  }

}
