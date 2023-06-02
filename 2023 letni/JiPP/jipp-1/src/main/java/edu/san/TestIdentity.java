// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

public class TestIdentity {

  public static void main(String[] args) {
    final var n = 5;
    final var m = 5;
    System.out.println(n == m);

    final var s1 = "abcd";
    final var s2 = "abcd";
    System.out.println(s1 == s2);

    final var b1 = new StringBuilder().append("abcd");
    new StringBuilder().append("abcd");
    final var s3 = b1.toString();
    final var s4 = b1.toString();
    System.out.println(s3 == s4);

    System.out.println(s3);
    System.out.println(s4);

    System.out.println(s3.equals(s4));

    final Integer n1 = 100;
    final Integer n2 = 100;
    System.out.println(n1 == n2);

    final Integer n3 = 10_000_000;
    final Integer n4 = 10_000_000;
    System.out.println(n3 == n4);
  }

}
