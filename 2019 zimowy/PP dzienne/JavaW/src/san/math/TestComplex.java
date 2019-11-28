// Copyright (c) Konrad Grzanek
// Created 27.11.2019
package san.math;

public class TestComplex {

  public static void main(String[] args) {
    Complex z = new Complex(1, 2);
    Complex r = new Complex(2);

    System.out.println(Complex.repr(z));
    System.out.println(Complex.repr(r));
    System.out.println(Complex.repr(
        new Complex(2, -3)));
    System.out.println(Complex.repr(
        new Complex(0, 3)));
  }

}
