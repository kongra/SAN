class Fibonacci {

  static long fib(long n) {
    if (n == 0)
      return 0;
    else if(n == 1)
      return 1;

    return fib(n-1) + fib(n-2);
  }

  public static void main(String[] args) {
    for (int n=0; n < 51; n++) {
      long value = fib(n);
      System.out.println("fib(" + n + ")=" + value);
    }

    // System.out.println(fib(0));
    // System.out.println(fib(1));
    // System.out.println(fib(2));
    // System.out.println(fib(3));
    // System.out.println(fib(4));
    // System.out.println(fib(0));
  }

}
