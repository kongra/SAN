package eshop.tests;


import org.junit.Test;

import eshop.currencies.Currency;
import eshop.currencies.Money;

public class TestMoney {

  @Test
  public void test() {
    long start = System.currentTimeMillis();
    for (int i = 0; i < 100000; i++) {
      Money x = Money.of(1500 + i, Currency.PLN);
      Money y = Money.of(750 + i, Currency.GBP);

      @SuppressWarnings("unused")
      Money z = x.add(y);
      // System.out.println(z);
    }
    System.out.println("Zakończyłem w " + (System.currentTimeMillis() - start));
  }

}
