package zus.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zus.money.Currency;
import zus.money.Money;
import zus.profile.ProfileTools;

@WebServlet("/test/servlet")
public class TestServlet extends HttpServlet {

  @EJB
  private ProfileTools profileTools;

  public TestServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html; charset=utf-8");
    response.setCharacterEncoding("UTF-8");

    try (PrintWriter out = response.getWriter()) {
      out.println("<h1>ąęśćółżźń</h1>");
    }

    profileTools.createProfile("jan", "1234", "Jan", "Kowalski", new Money(
        Currency.PLN, new BigDecimal("500.0")));

    profileTools.createEmployee("robert", "2345", "Robert", "Nowak",
      "Wydział Dochodów", new Money(Currency.USD, new BigDecimal("1500.0")));
  }

  private static final long serialVersionUID = 1L;
}
