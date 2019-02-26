class Fib {

  static int fib(int n) {
    if (n == 0 || n == 1)
      return n;

    return fib(n - 1) + fib(n - 2);
  }

  public static void main(String[] args) {
    System.out.println(fib(0));
    System.out.println(fib(1));
    System.out.println(fib(2));
    System.out.println(fib(3));
    System.out.println(fib(4));
    System.out.println(fib(5));

    System.out.println(fib(45));
    System.out.println(fib(46));
    System.out.println(fib(47));
  }

}
