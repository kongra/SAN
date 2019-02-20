class Euler1 {

  public static void main(String[] args) {
    int i = 0;
    int sum = 0;

    while(i < 1000) {
      if (i % 3 == 0 || i % 5 == 0) {
        sum += i;
        // System.out.println(i + " jest podzielne przez 3 lub 5");
      }

      // System.out.println(i);
      i += 1;
    }

    System.out.println("Wynik wynosi " + sum);
  }
}
