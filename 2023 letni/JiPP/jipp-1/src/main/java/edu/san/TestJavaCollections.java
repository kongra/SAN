// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestJavaCollections {

  public static void main(String[] args) {
    testMap();
  }

  public static void testMap() {
    Map<String, Integer> petsAges = new HashMap<>();
    petsAges.put("Azor", 3);
    petsAges.put("Kicia", 2);
    petsAges.put("Teodor", 8);
    petsAges.put("Dolly", 6);

    System.out.println(petsAges);
  }

  public static void testSet() {
    Set<String> names = new HashSet<>();
    names.add("John");
    names.add("Rosie");
    names.add("Andy");
    names.add("John");

    System.out.println(names);
  }

  public static void testList() {
    final List<String> names = new ArrayList<>();
    names.add("123");

    for (final var name : names) {
      System.out.println(name);
    }

    names.forEach(System.out::println);
  }

}
