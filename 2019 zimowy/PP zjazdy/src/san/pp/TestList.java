package san.pp;

import java.util.*;

public class TestList {

  public static void main(String... args) {
    List<String> fruits = new ArrayList<>();
    fruits.add("apple");
    fruits.add("banana");
    fruits.add("grapes");

    Map<String, Double> cals = new HashMap<>();
    cals.put("apple", (double) 24);
    cals.put("banana", (double) 200);
    cals.put("grapes", (double) 500);

//    List fruits = Arrays.asList(
//        "apple",
//        "banana",
//        "grapes");

//    List fruits = makeArrayList(
//        "apple",
//        "banana",
//        "grapes",
//        123
//    );

    System.out.println(fruits);
    digest(fruits, cals);
    System.out.println(fruits);
  }

  static void digest(List<String> fruits,
                     Map<String, Double> cals) {
    for (int i = 0, n = fruits.size(); i < n; i++) {
      System.out.println("Energy taken from "
          + fruits.get(i) + " is "
          + cals.get(fruits.get(i)));
    }

    Iterator<String> it = fruits.iterator();
    while (it.hasNext()) {
      String fruit = it.next();
      System.out.println("Energy taken from "
          + fruit + " is "
          + cals.get(fruit));
      // it.remove();
    }

    for (String fruit : fruits) {
      System.out.println("Energy taken from "
          + fruit + " is "
          + cals.get(fruit));
    }

    fruits.clear();
  }

  static ArrayList makeArrayList(Object... elements) {
    ArrayList l = new ArrayList(elements.length);
    for (Object e : elements) {
      l.add(e);
    }
    return l;
  }

  static ArrayList<String> makeFruitsList(String... fruits) {
    var l = new ArrayList<String>(fruits.length);
    for (String f : fruits) {
      l.add(f);
    }
    return l;
  }


}
