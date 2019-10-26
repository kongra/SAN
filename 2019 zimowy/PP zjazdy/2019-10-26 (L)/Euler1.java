class Euler1 {

  public static void main(String[] args) {
    int n = 0;
    int sum = 0;
    while(n <= 999) {
      if((n % 3 == 0) || (n % 5 == 0)) {
        // System.out.println(n);
        sum = sum + n;
      }
      n = n + 1;
    }

    System.out.println("Wynik " + sum);
  }

}
