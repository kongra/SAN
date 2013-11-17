package san.lect01;

public class Test01 {

  public static void main(String[] args) {
    // test01();
    
    double[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    
    System.out.println(matrix[0]);
  }

  private static void test01() {
    int[] w = {1, 2, 3, 4, 5, 6, 7};
    int[] v = {4, 5, 6, 7, 8};
    
    int product = 0;
    for(int i = 0; i < w.length && i < v.length; i++) {
      product += w[i] * v[i];
    }
    
    System.out.println(product);
    System.out.println(repr(v));
    System.out.println(repr(w));
  }

  private static String repr(int[] tab) {
    StringBuilder buf = new StringBuilder("[");
    
    for(int i = 0; i < tab.length; i++) {
      buf.append(tab[i]);
      if(i != tab.length - 1) {
        buf.append(", ");
      }
    }
    
    buf.append("]");
    return buf.toString();
  }

}
