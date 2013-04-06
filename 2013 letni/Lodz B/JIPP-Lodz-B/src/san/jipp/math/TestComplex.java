package san.jipp.math;

public class TestComplex {

  public static void main(String[] args) {
    Complex a = new Complex(1, 2); // 1 + 2i
    Complex b = new Complex(0, 1); // i

    // Complex c = a + b;

    Complex c = a.add(b);

//    a.print();
//    b.print();
//    c.print();

    Complex d = a.add(4);
//    d.print();

//    System.out.println(d.modulus());
//    System.out.println(d.argument());
//
//    Complex e = new Complex(0, 0);
//    System.out.println(e.modulus());
//    System.out.println(e.argument());

    Complex f = new Complex(1, 2);
    Complex g = new Complex(1, 2);

    System.out.println(f.equals(g));
    System.out.println(f.hashCode());
    System.out.println(g.hashCode());
    
    System.out.println(f);
  }

}
