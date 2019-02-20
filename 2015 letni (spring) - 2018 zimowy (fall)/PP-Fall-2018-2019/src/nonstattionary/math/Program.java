package nonstattionary.math;

public class Program {

  public static void main(String[] args) {
    Complex a = new Complex(1, 2);
    Complex b = new Complex(3, 4);
    Complex c = new Complex(3, 4);
    Complex d = c;

    double x = 0;
    double y = 1 / 3;

    double v = 0;
    double nan = x / v;

    System.out.println(b.equals(c));
    System.out.println(c.equals(d));

    // System.out.println(x == (z * y));
    // System.out.println(x == z);
  }

}
