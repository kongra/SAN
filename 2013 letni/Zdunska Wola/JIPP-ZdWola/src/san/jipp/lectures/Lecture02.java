package san.jipp.lectures;

import san.jipp.math.Complex;

public class Lecture02 {

  public static void main(String[] args) {
    Complex a, b;
    
    a = Complex.rectangular(1, 2);
    b = Complex.rectangular(0, -4);
    
    Complex c = a.add(245.2);
    System.out.println(c);
  }
  
}
