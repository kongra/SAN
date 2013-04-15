package san.jipp.lectures;

import san.jipp.math.Complex;

public class Lecture03 {

  public static void main(String[] args) {
    Complex a = new Complex(1, 2);
    Complex b = new Complex(1, 2);
   
    System.out.println(a.equals(b));
    System.out.println(a.hashCode());
    System.out.println(b.hashCode());
    
  }
  
}
