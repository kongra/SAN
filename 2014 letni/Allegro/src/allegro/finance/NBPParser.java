package allegro.finance;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class NBPParser {

  private static final String NBP_URL = "http://www.nbp.pl/kursy/xml/LastA.xml";

  private static final XPath xpath;

  private static final XPathExpression tableNumberExpression;

  private static final XPathExpression publicationDateExpression;

  private static final SimpleDateFormat publicationDateFormat =
      new SimpleDateFormat("yyyy-MM-dd");

  private static final XPathExpression positionExpression;

  private static final XPathExpression codeExpression;

  private static final XPathExpression nameExpression;

  private static final XPathExpression rateExpression;

  static {
    XPathFactory xPathfactory = XPathFactory.newInstance();
    xpath = xPathfactory.newXPath();

    tableNumberExpression = compile("/tabela_kursow/numer_tabeli");
    publicationDateExpression = compile("/tabela_kursow/data_publikacji");

    positionExpression = compile("/tabela_kursow/pozycja");
    nameExpression = compile("nazwa_waluty");
    codeExpression = compile("kod_waluty");
    rateExpression = compile("kurs_sredni");
  }

  private static final XPathExpression compile(String expr) {
    try {
      return xpath.compile(expr);
    }
    catch (XPathExpressionException e) {
      throw new RuntimeException(e);
    }
  }

  public static boolean parseAndUpdate(MoneyTools moneyTools,
      CurrencyRatesHolder holder, Document doc) {
    try {
      String tableNumber =
          (String) tableNumberExpression.evaluate(doc, XPathConstants.STRING);
      tableNumber = StringUtils.trim(tableNumber);

      String publicationDateString =
          (String) publicationDateExpression.evaluate(doc,
            XPathConstants.STRING);

      Date publicationDate = publicationDateFormat.parse(publicationDateString);

      // if (moneyTools.findCurrencyRatesByTableNumber(tableNumber) != null) {
      // return false;
      // }

      Map<Currency, Rate> rates = new HashMap<>();
      NodeList positions =
          (NodeList) positionExpression.evaluate(doc, XPathConstants.NODESET);

      for (int i = 0, n = positions.getLength(); i < n; i++) {
        Node pos = positions.item(i);
        parsePosition(moneyTools, rates, pos);
      }

      // moneyTools.createCurrencyRates(publicationDate, tableNumber, rates);
      return true;
    }
    catch (XPathExpressionException | ParseException e) {
      e.printStackTrace(System.err);
      return false;
    }
  }

  private static void parsePosition(MoneyTools moneyTools,
      Map<Currency, Rate> rates, Node pos) throws XPathExpressionException {
    String name = (String) nameExpression.evaluate(pos, XPathConstants.STRING);
    String code = (String) codeExpression.evaluate(pos, XPathConstants.STRING);
    String rateString =
        (String) rateExpression.evaluate(pos, XPathConstants.STRING);
    
    
    
  }

  public static Document grab() {
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder;
    try {
      dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(NBP_URL);
      doc.getDocumentElement().normalize();
      return doc;
    }
    catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace(System.err);
      return null;
    }
  }
}
