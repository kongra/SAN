class Factorial {

  static long factorial(int n) {
    if (n == 0) return 1;

    return n * factorial(n-1);
  }

  static long factorial1(int n) {
    long result = 1;
    for (int i = 1; i <= n; i++) {
      result = result * i;
    }

    return result;
  }

  public static void main(String[] args) {
    for (var i = 0; i <= 5000; i++) {
      var value = factorial1(i);
      System.out.println(i + "-ta silnia to " + value);
    }
  }

}
