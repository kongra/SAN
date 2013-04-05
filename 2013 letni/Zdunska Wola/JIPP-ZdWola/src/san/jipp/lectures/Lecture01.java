package san.jipp.lectures;

import san.jipp.math.Algebra;

public class Lecture01 {

  public static void main(String[] args) {
    double[] x = new double[] {3.0, 4.0, -5.0};
    double[] y = new double[] {1, -2, 3};
    
    double w = Algebra.scalar(x, y);
    System.out.println(w);
    
    
  }
  
}
