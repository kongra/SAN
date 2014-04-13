package eshop.currencies.nbp;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import eshop.currencies.Currency;
import eshop.currencies.CurrencyRates;
import eshop.currencies.CurrencyRatesHolder;
import eshop.currencies.MoneyTools;
import eshop.currencies.Rate;

@Stateless
public class NBPParser {

  private static final XPath XPATH;

  private static XPathExpression TABLE_NUMBER_EXPR;

  private static XPathExpression PUBLICATION_DATE_EXPR;

  private static final SimpleDateFormat PUBLICATION_DATE_FORMAT =
      new SimpleDateFormat("yyyy-MM-dd");

  private static XPathExpression POSITION_EXPR;

  private static XPathExpression CURRENCY_NAME_EXPR;

  private static XPathExpression CURRENCY_CODE_EXPR;

  private static XPathExpression RATE_EXPR;

  private static final DecimalFormat RATE_FORMAT = new DecimalFormat(
      "000000000000000,00000");

  static {
    XPathFactory xPathfactory = XPathFactory.newInstance();
    XPATH = xPathfactory.newXPath();

    try {
      TABLE_NUMBER_EXPR = XPATH.compile("/tabela_kursow/numer_tabeli");
      PUBLICATION_DATE_EXPR = XPATH.compile("/tabela_kursow/data_publikacji");
      POSITION_EXPR = XPATH.compile("/tabela_kursow/pozycja");

      CURRENCY_NAME_EXPR = XPATH.compile("nazwa_waluty");
      CURRENCY_CODE_EXPR = XPATH.compile("kod_waluty");
      RATE_EXPR = XPATH.compile("kurs_sredni");
    }
    catch (XPathExpressionException e) {
      e.printStackTrace();
    }
  }

  @EJB
  private CurrencyRatesHolder ratesBean;

  @EJB
  private MoneyTools moneyTools;

  @EJB
  private CurrencyRatesHolder ratesHolder;

  @PersistenceContext
  private EntityManager em;

  @Schedule(hour = "12", minute = "18", persistent = true)
  public void updateCurrencyRates() {
    Document doc = getXML();

    try {
      String tableNumber =
          (String) TABLE_NUMBER_EXPR.evaluate(doc, XPathConstants.STRING);

      // TODO: nie działać, gdy się okaże, że zestawienie już zostało wciągnięte
      // do systemu

      String publicationDateString =
          (String) PUBLICATION_DATE_EXPR.evaluate(doc, XPathConstants.STRING);
      Date publicationDate =
          PUBLICATION_DATE_FORMAT.parse(publicationDateString);

      Map<Currency, Rate> rates = new HashMap<>();
      NodeList positions =
          (NodeList) POSITION_EXPR.evaluate(doc, XPathConstants.NODESET);
      for (int i = 0, n = positions.getLength(); i < n; i++) {
        Node position = positions.item(i);
        parsePosition(position, rates);
      }

      CurrencyRates cr = new CurrencyRates(tableNumber, publicationDate, rates);
      em.persist(cr);

      ratesHolder.updateRaw(rates);

      System.out.println("Update finished.");
    }
    catch (XPathExpressionException | ParseException e) {
      throw new RuntimeException(e);
    }

  }

  private void parsePosition(Node position, Map<Currency, Rate> rates)
      throws XPathExpressionException, ParseException {
    String name =
        (String) CURRENCY_NAME_EXPR.evaluate(position, XPathConstants.STRING);

    String code =
        (String) CURRENCY_CODE_EXPR.evaluate(position, XPathConstants.STRING);

    String rateString =
        (String) RATE_EXPR.evaluate(position, XPathConstants.STRING);

    BigDecimal value =
        BigDecimal.valueOf(RATE_FORMAT.parse(rateString).doubleValue())
            .stripTrailingZeros();

    Currency c = moneyTools.internCurrency(code, name);
    Rate r = moneyTools.internRate(value);

    System.out.println("Parsed " + name + " of value " + value);

    rates.put(c, r);
  }

  private static Document getXML() {
    DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder = fact.newDocumentBuilder();
      return builder.parse("http://www.nbp.pl/kursy/xml/LastA.xml");
    }
    catch (ParserConfigurationException | SAXException | IOException e) {
      throw new RuntimeException(e);
    }
  }
}
