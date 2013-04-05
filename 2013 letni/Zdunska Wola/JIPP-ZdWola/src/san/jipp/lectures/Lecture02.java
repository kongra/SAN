package san.jipp.lectures;

import san.jipp.math.Complex;

public class Lecture02 {

  public static void main(String[] args) {
    Complex a, b;
    
    a = new Complex(1, 2);
    b = new Complex(0, -4);
    
    Complex c = a.add(b);
    
    System.out.println(c.repr());
  }
  
}
