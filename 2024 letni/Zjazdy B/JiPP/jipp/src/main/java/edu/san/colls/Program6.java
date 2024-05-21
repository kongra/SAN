// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.colls;

import java.util.ArrayList;
import java.util.List;

interface Program6 {

  static void main(String... args) {
    final var l1 = List.of();
    final var l2 = List.of();
    System.out.println(l1);
    System.out.println(l1.getClass());

    System.out.println(l1 == l2);

    final var l3 = List.of(1, 2, 3);
    System.out.println(l3);

    final var l4 = new ArrayList<Integer>(4);
    l4.add(1);
    l4.add(2);
    l4.add(3);
    l4.add(4);

    System.out.println(l4);
    l4.add(5);

    System.out.println(l4.get(0));

    final var it1 = l4.iterator();
    while (it1.hasNext()) {
      System.out.println(it1.next());
    }

    for (final Integer i : l4) {
      System.out.println(i);
    }

    l4.forEach(System.out::println);

  }

}
