package san.math;

public class TestComplex {

  public static void main(String[] args) {    
    Complex z1 = Complex.of(2, 3);
    Complex z2 = Complex.polar(2, Math.PI / 6);
    
    Complex z3 = z1.add(z2);
    Complex z4 = z2.add(z1);
    Complex z5 = z4.asPlanar();
  }

}
