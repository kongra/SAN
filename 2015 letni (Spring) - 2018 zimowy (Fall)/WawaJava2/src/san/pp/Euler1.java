package san.pp;

public class Euler1 {

  public static void main(String[] args) {
    int result = 0;
    for (int n = 0; n < 1000; n++) {
      if (isMultiple(3, n) || isMultiple(5, n)) {
        result += n;
      }
    }
    System.out.println(result);
  }

  private static boolean isMultiple(int i, int n) {
    return n % i == 0;
  }

}
