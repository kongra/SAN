package san.prog.math;

import java.math.BigDecimal;

public class Lect06 {

  public static void main(String... args) {
    Ratio a = new Ratio(1, -3);
    Ratio b = new Ratio(-1, 3);
    
    System.out.println(a.equals(b));
  }

  private static void test01() {
    Ratio a = new Ratio(4, 5);
    Ratio b = new Ratio(1, 3);
    
    Ratio c = a.add(b);
    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
  }

  private static void test02() {
    double netto = 10.0;
    double tax = 0.0825;
    
    double brutto = netto * (1 + tax);
    System.out.println(brutto);
    
    BigDecimal x = BigDecimal.valueOf(13);
    BigDecimal y = BigDecimal.valueOf(99);
    
    System.out.println(x.divide(y, 500, BigDecimal.ROUND_UP));
  }
  
}
