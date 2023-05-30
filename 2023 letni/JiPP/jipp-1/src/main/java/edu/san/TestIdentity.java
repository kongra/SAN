// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san;

public class TestIdentity {

  public static void main(String[] args) {
    int n = 5;
    int m = 5;
    System.out.println(n == m);

    String s1 = "abcd";
    String s2 = "abcd";
    System.out.println(s1 == s2);

    StringBuilder b1 = new StringBuilder().append("abcd");
    StringBuilder b2 = new StringBuilder().append("abcd");
    String s3 = b1.toString();
    String s4 = b1.toString();
    System.out.println(s3 == s4);

    System.out.println(s3);
    System.out.println(s4);

    System.out.println(s3.equals(s4));

    Integer n1 = 100;
    Integer n2 = 100;
    System.out.println(n1 == n2);

    Integer n3 = 10_000_000;
    Integer n4 = 10_000_000;
    System.out.println(n3 == n4);
  }

}
