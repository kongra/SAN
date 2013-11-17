package san.lect01;

import san.math.Complex;

public class TestComplex {

  public static void main(String... args) {
    Complex a = Complex.planar(1, 2);
    Complex b = Complex.polar(1, Math.PI / 4);

    Complex c = a.add(b);
    Complex d = c.asPolar();
    
    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
    System.out.println(d);
  }

}
