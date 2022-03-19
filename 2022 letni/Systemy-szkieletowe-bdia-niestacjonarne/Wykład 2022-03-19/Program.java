class Program {

  static void foo(int i) {
    int m = 4;
    goo(m);
  }

  static void goo(int j) {
    int[] tab = new int[j];
    tab[0] = 5;
  }

  public static void main(String... args) {
    int n = 3;
    foo(n);
  }

}
