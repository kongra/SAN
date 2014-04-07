package zus.finance.nbp;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
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

import zus.finance.Currency;
import zus.finance.MoneyTools;
import zus.finance.Rate;
import zus.finance.RatesColl;

public class NBPParser {

  private static final XPath XPATH = XPathFactory.newInstance().newXPath();

  private static XPathExpression PUBLICATION_DATE_EXPR;

  private static XPathExpression POSITION_EXPR;

  private static XPathExpression CURRNAME_EXPR;

  private static XPathExpression CURRCODE_EXPR;

  private static XPathExpression RATE_EXPR;

  private static final SimpleDateFormat PUBLICATION_DATE_FORMAT =
      new SimpleDateFormat("yyyy-MM-dd");

  static {
    try {
      PUBLICATION_DATE_EXPR = XPATH.compile("/tabela_kursow/data_publikacji");
      POSITION_EXPR = XPATH.compile("/tabela_kursow/pozycja");
      CURRNAME_EXPR = XPATH.compile("nazwa_waluty");
      CURRCODE_EXPR = XPATH.compile("kod_waluty");
      RATE_EXPR = XPATH.compile("kurs_sredni");
    }
    catch (XPathExpressionException e) {
      e.printStackTrace(System.err);
    }
  }

  private final MoneyTools moneyTools;

  private final EntityManager em;

  private final SessionContext ctx;

  public NBPParser(MoneyTools moneyTools, EntityManager em, SessionContext ctx) {
    this.moneyTools = moneyTools;
    this.em = em;
    this.ctx = ctx;
  }

  public boolean parse() {
    final String URL = "http://www.nbp.pl/kursy/xml/a066z140404.xml";
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder = dbFactory.newDocumentBuilder();
      Document doc = builder.parse(URL);
      doc.getDocumentElement().normalize();

      System.out.println(doc);

      String publicationDateString = findPublicationDate(doc);
      Date publicationDate =
          PUBLICATION_DATE_FORMAT.parse(publicationDateString);
      System.out.println("Data publikacji: " + publicationDate);

      Map<Currency, Rate> rates = processRates(doc);
      RatesColl rc = new RatesColl(publicationDate, rates);
      em.persist(rc);

      // TODO: Aktualizować RatesHolder

      System.out
          .println("Proces importu nowego zestawienia kursów zakończony.");
      return true;
    }
    catch (ParserConfigurationException | SAXException | IOException
        | XPathExpressionException | ParseException e) {
      e.printStackTrace(System.err);
      ctx.setRollbackOnly();
      return false;
    }
  }

  private Map<Currency, Rate> processRates(Document doc)
      throws XPathExpressionException, ParseException {
    NodeList positions =
        (NodeList) POSITION_EXPR.evaluate(doc, XPathConstants.NODESET);

    Map<Currency, Rate> currencyToRates = new HashMap<>();

    for (int i = 0, n = positions.getLength(); i < n; i++) {
      processPositionNode(positions.item(i), currencyToRates);
    }
    return null;
  }

  private void processPositionNode(Node positionNode,
      Map<Currency, Rate> currencyToRates) throws XPathExpressionException,
      ParseException {
    String name =
        (String) CURRNAME_EXPR.evaluate(positionNode, XPathConstants.STRING);
    String code =
        (String) CURRCODE_EXPR.evaluate(positionNode, XPathConstants.STRING);
    String rateString =
        (String) RATE_EXPR.evaluate(positionNode, XPathConstants.STRING);

    Currency curr = moneyTools.internCurrency(code, name);

    DecimalFormat valueFormat = new DecimalFormat("#,####");
    BigDecimal rateValue =
        new BigDecimal(valueFormat.parse(rateString).toString());
    Rate rate = moneyTools.internRate(rateValue);

    currencyToRates.put(curr, rate);
    System.out.println("Processed " + name + " " + code + " " + rateValue);
  }

  private String findPublicationDate(Document doc)
      throws XPathExpressionException {
    return (String) PUBLICATION_DATE_EXPR.evaluate(doc, XPathConstants.STRING);
  }

}
