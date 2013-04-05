package san.jipp;

import san.jipp.math.Linalg;

public class Program002 {

  public static void main(String[] args) {
    double[][] a = new double[][] {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };
    
    ArrayTools.print(a[0]);
    ArrayTools.print(a[1]);
    ArrayTools.print(a[2]);
    
    System.out.println(a[0][1]);
    
    double[] x = new double[] {5, -2, 4};
    double[] y = Linalg.mutl(a, x);
    
    ArrayTools.print(y);
    
  }

}
