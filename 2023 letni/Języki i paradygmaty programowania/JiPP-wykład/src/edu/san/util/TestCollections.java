// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestCollections {

  static record Enemy(String name) {}

  static <T> Set<Set<T>> powset(Set<T> s) {
    return Set.of(); // ... ;
  }

  public static void main(String[] args) {
    Map<Enemy, Double> strengths = new HashMap<>();
    strengths.put(new Enemy("Troll"), 27357623.0);
    strengths.put(new Enemy("Snake"), 357623.0);

    System.out.println(strengths);
    System.out.println(strengths.get(new Enemy("Troll")));
  }

  public static void testSet() {
    Set<Enemy> enemies = new HashSet<>();
    enemies.add(new Enemy("Troll"));
    enemies.add(new Enemy("Dragon"));
    enemies.add(new Enemy("Troll"));
    enemies.add(new Enemy("Troll"));
    enemies.add(new Enemy("Spider"));

    enemies.forEach(System.out::println);
  }

  public static void testList() {
    List<Enemy> enemies = new ArrayList<>();
    enemies.add(new Enemy("Troll"));
    enemies.add(new Enemy("Dragon"));
    enemies.add(new Enemy("Troll"));
    enemies.add(new Enemy("Troll"));

    // System.out.println(enemies);
    // enemies.forEach(System.out::println);

    for (var enemy : enemies) {
      System.out.println(enemy);
    }

    Iterator<Enemy> it = enemies.iterator();
    while (it.hasNext()) {
      System.out.println(it.next());
    }
  }

}
