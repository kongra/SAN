package edu.san.math;

public class TestComplex {

  public static void main(String[] args) {
    Complex c1 = new Complex(3, 4);
    Complex c2 = new Complex(1, 2);
    Complex r = c1.add(c2);
    System.out.println(r.modulus());

    System.out.println(
        new Complex(5,6)
            .add(new Complex(7, 8))
            .add(new Complex(2, 1))
            .add(new Complex(4, 2)).asString());

    System.out.println(new Complex(1, 2).asString());
    System.out.println(new Complex(-1, 2).asString());
    System.out.println(new Complex(1, -2).asString());
    System.out.println(new Complex(-1, -2).asString());
    System.out.println(new Complex(0, 2).asString());
    System.out.println(new Complex(-1, 0).asString());
    System.out.println(new Complex(0, -2).asString());
    System.out.println(new Complex(0, 0).asString());
  }

}
