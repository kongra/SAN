package san.algo;

public class Tabs {

  public static void print(double[] x) {
    System.out.print("[");
    for (int i = 0; i < x.length; i++) {
      System.out.print(x[i]);
      if (i != x.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println("]");
  }

}
