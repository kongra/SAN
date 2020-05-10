package san.utils;

public class TestArraySeq {

  public static void main(String... args) {
    ArraySeq<Object> s1 = new ArraySeq(10);
    System.out.println(s1);

    s1.add(4).add(5).add(-1);
    System.out.println(s1);

    s1.set(0, 3).set(2, "abc");
    System.out.println(s1);

    for (var e : s1) {
      System.out.println(e);
    }

    ArraySeq<Integer> s2 = new ArraySeq(7,
        3, 4, 5);

    s2.add(6).add(7);
    System.out.println(s2);

    int sum = 0;
    for (var e : s2) {
      sum += e;
    }

    System.out.println("sum=" + sum);

    ArraySeq<String> s3 = new ArraySeq<>(10,
        "ab", "xyz", "www", "Ala ma kota");
    ArraySeq<Integer> s4 = s3.map(String::length);

    System.out.println(s3);
    System.out.println(s4);
  }

}
