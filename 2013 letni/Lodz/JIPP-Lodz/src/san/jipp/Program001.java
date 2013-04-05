package san.jipp;

import san.jipp.math.Linalg;

public class Program001 {

  public static void main(String[] args) {
    double[] x1 = new double[] {
        1, 0, 3
    };
    double[] x2 = new double[] {
        0, 1
    };

    double sc = Linalg.scalarMult(x1, x2);
    System.out.println(Linalg.areOrthogonal(x1, x2));

  }

}
