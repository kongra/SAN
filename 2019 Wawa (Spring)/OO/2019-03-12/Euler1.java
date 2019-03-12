class Euler1 {

  static boolean isDivisible(int n, int m) {
    return n % m == 0;
  }

  public static void main(String[] args) {
    int n = 0;
    int sum = 0;
    while(n < 1000) {
      if (isDivisible(n, 3) || isDivisible(n, 5)) {
        // System.out.println(n);
        sum += n;
      }
      n += 1;
    }

    System.out.println("The result is " + sum);
  }

}
