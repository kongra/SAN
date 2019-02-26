package san.pp;

public class Euler5 {

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    for (long n = 1; ; n++) {
      if (isDivisibleByEvery(n, 1, 20)) {
        System.out.println(n);
        break;
      }
    }
    long end = System.currentTimeMillis();
    System.out.println("It took me " + (end - start) + " msecs to compute");
  }

  private static boolean isDivisibleByEvery(long n, long start, long end) {
    for (long i = start; i <= end; i++) {
      if (n % i != 0) return false;
    }
    return true;
  }

}
