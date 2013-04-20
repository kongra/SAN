package san.jipp;

import san.jipp.math.Complex;

public class TestComplex {

  public static void main(String[] args) {
    Complex a = Complex.algebraic(1, 2);
    Complex b = Complex.polar(2, Math.PI / 4);

    Complex c = a.add(b);

    System.out.println(a.algebraic());
    System.out.println(b.algebraic());
    System.out.println(c.algebraic());
  }

}
