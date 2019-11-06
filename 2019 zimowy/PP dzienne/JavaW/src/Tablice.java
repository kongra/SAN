public class Tablice {

  static String asString(int[] tab) {
    StringBuilder buf = new StringBuilder("[");
    int i = 0;
    while (i < tab.length) {
      buf.append(tab[i]);
      if (i != (tab.length - 1)) {
        buf.append(",");
      }
      i += 1;
    }
    return buf.append("]").toString();
  }

  public static void main(String[] args) {
    int[] tab = {3, 4, 2, 7, 8, 9};
    System.out.println(asString(tab));

  }

}
