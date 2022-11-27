class Fib {

  static long fib(int n) {
    if (n == 0 || n == 1) {
      return n;
    }

    return fib(n-1) + fib(n-2);
  }

  public static void main(String[] args) {
    for (int i = 0; i <= 50; i++) {
      long value = fib(i);
      System.out.println(i + "-ty wyraz ciągu to " + value);
    }
  }

}
