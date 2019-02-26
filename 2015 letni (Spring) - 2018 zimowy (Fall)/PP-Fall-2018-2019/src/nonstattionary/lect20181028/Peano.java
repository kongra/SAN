package nonstattionary.lect20181028;

public class Peano {

  static int add(int x, int y) {
    if (x == 0)
      return y;
    else
      return add(dec(x), inc(y));
  }

  static int add1(int x, int y) {
    while (true) {
      if (x == 0)
        return y;
      else {
        x = dec(x);
        y = inc(y);
      }
    }
  }

  static int inc(int x) {
    return x + 1;
  }

  static int dec(int x) {
    return x - 1;
  }

  static int plus(int x, int y) {
    if (x == 0)
      return y;
    else
      return inc(plus(dec(x), y));
  }

  public static void main(String[] args) {
    System.out.println(add(3, 4));
  }


}
