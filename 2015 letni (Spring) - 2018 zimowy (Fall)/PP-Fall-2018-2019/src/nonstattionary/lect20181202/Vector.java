package nonstattionary.lect20181202;

public class Vector {

  public static double dotProduct(double[] x, double[] y) {
    double result = 0;

    int n = Math.max(x.length, y.length);
    for (int i = 0; i < n; i++) {
      result += x[i] * y[i];
    }
    return result;
  }

  public static boolean areOrthogonal(double[] x, double[] y) {
    return dotProduct(x, y) == 0;
  }

  public static void main(String[] args) {
    double[] tab = {
        3, 4, -1
    };

    double[] tab1 = new double[4];
//    for (int i = 0; i < 3; i++) {
//      tab1[i] = tab[i];
//    }

    System.arraycopy(tab, 0,
        tab1, 0, 3);

    System.out.println(tab[0]);
    System.out.println(tab1.length);

    double[] x = {1, 2, 7};
    double[] y = {4, 5, 6};

    System.out.println(dotProduct(x, y));

  }

}
