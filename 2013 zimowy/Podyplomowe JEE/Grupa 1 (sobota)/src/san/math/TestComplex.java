package san.math;

import org.junit.Test;

public class TestComplex {

  @Test
  public void test() {
    // fail("Not yet implemented");

    Complex<Double> z = Complex.carthesian(1.0, 2.0);
    Complex<Float> c = Complex.polar(4.0f, (float) Math.PI / 4);
    
    Complex<Complex<Double>> re = null; // = ...
    Complex<Complex<Double>> im = null; // = ...
    
    @SuppressWarnings("unused")
    Complex<Complex<Complex<Double>>> a = Complex.carthesian(re, im);

    z.add(c);

  }

}
