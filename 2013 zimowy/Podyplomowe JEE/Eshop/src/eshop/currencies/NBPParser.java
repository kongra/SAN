package eshop.currencies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

@Stateless
public class NBPParser {

  @EJB
  private CurrencyRatesBean ratesBean;

  @PersistenceContext
  private EntityManager em;

  public void loadCurrencyRates() {
    Document doc = getXML();
    System.out.println(doc);
    System.out.println("childNodes : " + doc.getChildNodes());
  }

  private static Document getXML() {
    DocumentBuilderFactory fact = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder builder = fact.newDocumentBuilder();
      return builder.parse("http://www.nbp.pl/kursy/xml/a061z140328.xml");
    }
    catch (ParserConfigurationException | SAXException | IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
