// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san.colls;

import java.util.HashSet;
import java.util.Set;

interface Program8 {

  static void main(String... args) {
    Set<String> s1 = new HashSet<>();
    // 1. Brak uporządkowania
    // 2. Gwarancja unikalności

    s1.add("one");
    s1.add("two");
    s1.add("one");

    System.out.println(s1);
  }

}
