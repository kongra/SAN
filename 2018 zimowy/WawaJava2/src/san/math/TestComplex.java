package san.math;

public class TestComplex {

  public static void main(String[] args) {
    Complex a = Complex.planar(3, 4);
    Complex b = Complex.polar(5, 0.92729);

    System.out.println(a);
    System.out.println(b);

    System.out.println(a.asPolar());
    System.out.println(b.asPlanar());
  }

}
