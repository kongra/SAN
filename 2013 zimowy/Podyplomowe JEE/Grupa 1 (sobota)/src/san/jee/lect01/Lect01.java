package san.jee.lect01;

public class Lect01 {

  public static void main(String[] args) {
    // test1();
    // test2();
    
    byte b = 1;
    short s = 2;
    
    @SuppressWarnings("unused")
    int x = b + s;
  }

  private static void test2() {
    double[][] a = {{1, 2, 3}, {3, 4, 5}, {6, 7, 8}};
    
    @SuppressWarnings("unused")
    double[] b = a[0];
  }

  private static void test1() {
    double[] w = {1, 2, 3};
    double[] v = {4, 5, 6};
    
    double x = product(w, v);
    
    System.out.println(w);
    System.out.println(w[0]);
    System.out.println(x);
  }

  private static double product(double[] w, double[] v) {
    if(w.length != v.length) {
      throw new IllegalArgumentException("Wektory nie są tego samego wymiaru");
    }
    
    double result = 0;
    for (int i = 0; i < w.length; i++) {
      result += w[i] * v[i];
    }
    
    return result;
  }

}
