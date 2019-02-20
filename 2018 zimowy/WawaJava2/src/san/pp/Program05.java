package san.pp;

public class Program05 {

  static void printArray(int[] array) {
    System.out.print("[");
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i]);
      if (i < array.length - 1) System.out.print(",");
    }
    System.out.print("]");
  }

  public static void main(String[] args) {
    int[] p = {3, 4, -6, -2, 0, 7, 5};
    printArray(p);

    // v1 = [1, 2, 5]
    // v2 = [3, 4]
    // v1 * v2 = 1 * 3 + 2 * 4


  }

}
