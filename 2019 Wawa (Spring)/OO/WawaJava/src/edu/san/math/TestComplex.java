package edu.san.math;

public class TestComplex {

  public static void main(String[] args) {
    Complex z1 = Complex.planar(2, 1);
    System.out.println(z1.asString());
    System.out.println(z1.asPolar().asString());

    Complex z2 = Complex.polar(2, 3);
    Complex z3 = z2.add(Complex.polar(4, 5));
    System.out.println(z3.asString());
  }

}
