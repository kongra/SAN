class Euler1 {

  static boolean isMultipleOf(int i, int n) {
    return i % n == 0;
  }

  public static void main(String[] args) {
    long sum = 0;

    for (int i = 0; i < 1000; i++) {
      if (isMultipleOf(i, 3) || isMultipleOf(i, 5)) {
        // System.out.println(i);
        // sum += i;
        sum = sum + i;
      }
    }

    System.out.println(sum);
  }

}
