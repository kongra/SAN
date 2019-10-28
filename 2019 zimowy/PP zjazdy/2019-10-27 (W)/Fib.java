class Fib {

  static int fib(int n) {
    if (n == 0 || n == 1) {
      return n;
    } else {
      return fib(n-1) + fib(n-2);
    }
  }

  public static void main(String[] args) {
    System.out.println("Fib(53) => " + fib(53));

    // System.out.println("Fib(0) => " + fib(0));
    // System.out.println("Fib(1) => " + fib(1));
    // System.out.println("Fib(2) => " + fib(2));
    // System.out.println("Fib(3) => " + fib(3));
    // System.out.println("Fib(4) => " + fib(4));
    // System.out.println("Fib(5) => " + fib(5));
    // System.out.println("Fib(6) => " + fib(6));
    // System.out.println("Fib(7) => " + fib(7));
    // System.out.println("Fib(8) => " + fib(8));

  }
}
