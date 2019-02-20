package stationary.lect20181031;

public class Fib {

  static long fib(long n) {
    if (n == 0 || n == 1)
      return n;
    else
      return fib(n - 1) + fib(n - 2);
  }

  public static void main(String[] args) {
    System.out.println(fib(47));
  }

}
