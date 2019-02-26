class Euler1a {

  static boolean divisibleBy3(int n) {
    return n % 3 == 0;
  }

  static boolean divisibleBy5(int n) {
    return n % 5 == 0;
  }

  public static void main(String[] args) {
    int i = 0;
    int sum = 0;
    while(i < 1000) {
      if (divisibleBy3(i) || divisibleBy5(i)) {
        sum += i;
      }
      i += 1;
    }

    System.out.println(sum);
  }

}
