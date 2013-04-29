package san.jipp.lectures;

import san.jipp.math.Complex;

public class Lecture03 {

  public static void main(String[] args) {
    Complex a = Complex.rectangular(1, 2);
    System.out.println(a);
    System.out.println(a.toPolar());
  }
  
}
