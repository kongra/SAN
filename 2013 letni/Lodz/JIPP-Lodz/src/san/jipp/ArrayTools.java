package san.jipp;

public class ArrayTools {

  public static void print(double[] tab) {
    System.out.print("[");
    for (int i = 0; i < tab.length; i++) {
      System.out.print(tab[i]);
      if (i != tab.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.print("]");
  }

  public static void print(int[] tab) {
    System.out.print("[");
    for (int i = 0; i < tab.length; i++) {
      System.out.print(tab[i]);
      if (i != tab.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.print("]");
  }
}
