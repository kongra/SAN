package san.algo.math;

public class Functions {

  public static long factorial(long n) {
    if (n == 0) {
      return 1;
    }
    return n * factorial(n - 1);
  }

  public static long factorialIter(long n, long result) {
    if (n == 0) {
      return result;
    }
    return factorialIter(n - 1, n * result);
  }

  public static void main(String... args) {
    long start = System.currentTimeMillis();

    for (int i = 0; i < 1000000; i++) {
      long value = Functions.factorialIter(5, 1);
    }

    System.out.println("Wykonano w " + (System.currentTimeMillis() - start)
        + " msecs.");
  }
}
