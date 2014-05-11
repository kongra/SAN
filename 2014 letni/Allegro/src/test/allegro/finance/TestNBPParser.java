package test.allegro.finance;

import org.junit.Test;
import org.w3c.dom.Document;

import allegro.finance.NBPParser;

public class TestNBPParser {

  @Test
  public void test() {
    Document doc = NBPParser.grab();
    NBPParser.parseAndUpdate(null, null, doc);
  }

}
