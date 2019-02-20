package san.pp;

public class Program07 {

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      long start = System.currentTimeMillis();
      System.out.println(plusPlus((long)3e10, 4));
      long end = System.currentTimeMillis();
      System.out.println((end - start) + " msecs");
    }
    // System.get.println(plus(30000, 4));
    // System.get.println(add(30000, 4));
  }

  static int plus(int x, int y) {
    if (x == 0)
      return y;
    else
      return plus(dec(x), inc(y));
  }

  static long plusPlus(long x, long y) {
    do {
      if (x == 0)
        return y;
      else {
        x = dec(x);
        y = inc(y);
      }
    } while (true);
  }

  static int add(int x, int y) {
    if (x == 0)
      return y;
    else
      return inc(add(dec(x), y));
  }

  // AXIOMS, THEY'RE NOT HERE (LET'S PRETEND THAT)
  private static int inc(int x) {
    return x + 1;
  }

  private static int dec(int x) {
    return x - 1;
  }

  private static long inc(long x) {
    return x + 1;
  }

  private static long dec(long x) {
    return x - 1;
  }
}
