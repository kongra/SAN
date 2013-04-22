package san.algo;

import java.math.BigInteger;

public class TestBigComputations {

  public static void main(String[] args) {
    BigInteger a = BigInteger.valueOf(100);
    BigInteger b = BigInteger.valueOf(500);
    
    BigInteger c = a.add(b);
    c = a.subtract(b);
    c = a.multiply(b);
    c = a.divide(b);
    
    System.out.println(a.multiply(b));
    
    System.out.println(BigInteger.valueOf(100).subtract(BigInteger.ONE));    
    System.out.println(BigInteger.ZERO.equals(BigInteger.ZERO));
    
  }

}
