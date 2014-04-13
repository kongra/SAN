package eshop.tests;

import org.junit.Test;

import eshop.currencies.nbp.NBPParser;

public class TestNBPParser {

  @Test
  public void test() {
    NBPParser parser = new NBPParser();
    parser.updateCurrencyRates();
    
  }

}
