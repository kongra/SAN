package san.math;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestComplex {

  @Test
  public void test() {
    // fail("Not yet implemented");

    Complex z = Complex.carthesian(1, 2);
    Complex c = Complex.polar(4, Math.PI / 4);

    System.out.println(c.add(z).asCarthesian());

  }

}
