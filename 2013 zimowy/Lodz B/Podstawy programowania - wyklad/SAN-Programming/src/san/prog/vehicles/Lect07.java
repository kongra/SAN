package san.prog.vehicles;

import san.prog.math.Complex;

public class Lect07 {

  public static void main(String[] args) {
    // Samochód wołga = new Samochód();
    // System.out.println(wołga.kierownica);

    Complex a = new Complex(1, 2);
    Complex b = new Complex(3, 4);

    Complex c = a.add(b);

    System.out.println(c.re() + "+" + c.im() + "j");
  }

}
