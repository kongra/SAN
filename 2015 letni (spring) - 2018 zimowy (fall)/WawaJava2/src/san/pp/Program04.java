package san.pp;

public class Program04 {

  public static void main(String[] args) {
    test1();
    test2();
  }

  static void test1() {
    int n = 5;
    int s = 0;
    for (int i = 0; i <= n; i = i + 1) {
      s = s + i;
    }
    System.out.println(s);
  }

  static void test2() {
    int n = 5;
    int s = 0;
    int i = 0;
    while (i <= n) {
      s = s + i;
      i = i + 1;
    }
    System.out.println(s);
  }

}
