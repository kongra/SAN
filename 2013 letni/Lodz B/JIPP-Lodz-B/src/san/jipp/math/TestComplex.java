package san.jipp.math;

public class TestComplex {

  public static void main(String[] args) {
    Complex a = new Complex(1, 2); // 1 + 2i
    Complex b = new Complex(0, 1); // i

    // Complex c = a + b;

    Complex c = a.add(b);

    a.print();
    b.print();
    c.print();

    Complex d = a.add(4);
    d.print();
  }

}
