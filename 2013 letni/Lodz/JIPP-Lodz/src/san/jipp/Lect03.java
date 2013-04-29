package san.jipp;

import san.jipp.math.Complex;

public class Lect03 {

  public static void main(String[] args) {
    Complex z = Complex.rectangular(1, 2);
    Complex w = Complex.rectangular(1, 2);
    
    z = z.toPolar();
    w = w.toPolar();
    
    System.out.println(z.equals(w));
    System.out.println(z.hashCode());
    System.out.println(w.hashCode());
  }

}
