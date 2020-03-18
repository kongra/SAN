package san.jipp;

import java.util.*;

public class TestCollections {

  public static void main(String... args) {
    List l1 = new ArrayList();
    l1.add(1);
    l1.add("abcd");
    l1.add(3.14);

    for (var e : l1) {
      System.out.println(e.getClass());
    }

    Map m1 = new HashMap();
    m1.put("John", 4000);
    m1.put("Tom", 2000);

    System.out.println(m1.get("Adam"));
    System.out.println(m1.getOrDefault("Adam"
        , 1000));

    Set s1 = new HashSet();
    s1.add(1);
    s1.add("xyz");
    s1.add(1);

    System.out.println(s1);
  }

}
