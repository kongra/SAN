package san.math;

public class TestComplex {

  public static void main(String... args) {
    Complex c1 = new Complex(1, 2);
    Complex c2 = new Complex(3, 4);
    Complex c3 = c1;
    Complex c4 = new Complex(1, 2);

    System.out.println(c1.equals(c1));
    System.out.println(c1.equals(c2));
    System.out.println(c1.equals(c3));
    System.out.println(c1.equals(c4));
  }

}
