package san.jipp;

import san.jipp.math.Complex;

public class Lect04 {

  public static void main(String[] args) {
    Complex a = Complex.rectangular(1, -2).toPolar();
    Complex b = Complex.rectangular(-1, 0);
    
    Complex c = a.add(b);
    
    String cs = c.toString();
    System.out.println(cs);
  }
  
}
