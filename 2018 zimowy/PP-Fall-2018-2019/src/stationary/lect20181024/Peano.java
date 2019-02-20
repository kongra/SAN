package stationary.lect20181024;

class Peano {

  static long inc(long n) { return n + 1; }
  static long dec(long n) { return n - 1; }

  static long add(long m, long n) {
    if (m == 0)
      return n;
    else
      return add(dec(m), inc(n));
  }

  static long addTail(long m, long n) {
    while (true) {
      if (m == 0)
        return n;
      else {
        m = dec(m);
        n = inc(n);
      }
    }
  }

  public static void main(String[] args) {
    System.out.println(add(3, 4));
  }

}
