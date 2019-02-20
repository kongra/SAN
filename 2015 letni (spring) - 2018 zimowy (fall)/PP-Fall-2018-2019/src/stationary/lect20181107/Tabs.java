package stationary.lect20181107;

public class Tabs {

  static void printTabInt(int[] tab) {
    // int n = tab.length;
    int i = 0;
    while(i < tab.length /* n */) {
      System.out.println(tab[i]);
      i += 1;
    }
  }

  static void printTabInt1(int[] tab) {
    for (int i = 0; i < tab.length; i += 1) {
      System.out.println(tab[i]);
    }
  }

  static void printTabInt2(int[] tab) {
    for (int n: tab) {
      System.out.println(n);
    }
  }

  public static void main(String[] args) {
//    int[] tab = new int[5];
//    tab[0] = 1;
//    tab[1] = 1;
//    tab[2] = 2;
//    tab[3] = 3;
//    tab[4] = 5;

//    int[] tab = new int[] {
//        1, 1, 2, 3, 5
//    };

    int[] tab = {1, 1, 2, 3, 5};
    int[] tab1 = tab;

    // System.out.println(tab);
    printTabInt2(tab);
  }

}
