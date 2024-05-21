// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.colls;

import java.util.HashMap;
import java.util.Map;

interface Program7 {

  static void main(String... args) {
    Map<String, Integer> m1 = new HashMap<>();
    m1.put("one", 1);
    m1.put("two", 2);
    m1.put("three", 3);

    // String s = m1.get(256);

    System.out.println(m1);
    System.out.println(m1.get("two"));

    for (String k : m1.keySet()) {
      System.out.println(k);
    }

    m1.values().forEach(System.out::println);
    m1.entrySet().forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
    m1.forEach((k, v) -> System.out.println(k + " -> " + v));
  }

}
