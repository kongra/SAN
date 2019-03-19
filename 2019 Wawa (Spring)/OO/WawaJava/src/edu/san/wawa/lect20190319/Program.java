package edu.san.wawa.lect20190319;

public class Program {

  static long factorial(int n) {
    if (0 == n)
      return 1L;

    long result = 1L;
    for (int i = 1; i <= n; i++) {
      result *= i;
    }
    return result;
  }

  public static void main(String[] args) {
    // System.out.println(factorial(6));

    Car c;
    c = null;
    c = new Car();
    c.start();
    System.out.println(c);
  }

}
