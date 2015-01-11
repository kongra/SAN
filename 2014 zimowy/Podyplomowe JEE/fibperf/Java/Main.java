package san.math;

public class Main {

  public static void main(String[] args) {
    for (int i = 0; i < 5000; i++) {
      run1();
    }
  }

  private static void run1() {
    long start = System.currentTimeMillis();
    System.out.println(Fib.fibcount(37));
    System.out.println(Fib.fibcount(38));
    System.out.println(Fib.fibcount(39));
    long end = System.currentTimeMillis();
    System.out.println("Finished in " + (end - start) + " msecs.");
  }

}
