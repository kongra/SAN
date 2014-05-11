package eshop.profile;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import eshop.currencies.Money;
import eshop.currencies.MoneyTools;

@WebServlet("/profile/Register")
public class Register extends HttpServlet {

  @EJB
  private ProfileTools profileTools;

  @EJB
  private MoneyTools moneyTools;

  public Register() {
    super();
  }

  @Override
  protected void doPost(final HttpServletRequest request,
      final HttpServletResponse response) throws ServletException, IOException {

    final String login = request.getParameter("login");
    final String password = request.getParameter("password");
    final String firstName = request.getParameter("firstName");
    final String lastName = request.getParameter("lastName");

    if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
      System.out.println("Brak loginu i/lub hasła!!!");
      response.sendRedirect("./register.jsp");
      return;
    }

    final Address address = new Address();
    address.setStreet("Tokarzewskiego");
    address.setCity("Łódź");
    address.setCountry("Poland");
    address.setNumber("2a");

    final Gender gender = Gender.MALE;

    MoneyTools.binding(moneyTools, new Runnable() {
      @SuppressWarnings("synthetic-access")
      @Override
      public void run() {
        Profile profile =
            profileTools.registerB2C(login, password, firstName, lastName,
              address, gender, Money.of(3500, "PLN"));

        if (profile == null) {
          try {
            response.sendRedirect("./register.jsp");
          }
          catch (IOException e) {
            throw new RuntimeException(e);
          }
          System.out.println("Login już istnieje!!!");
          return;
        }

        request.getSession().setAttribute(Profile.TAG, profile);
        try {
          response.sendRedirect("../index.jsp");
        }
        catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    });

  }

  private static final long serialVersionUID = 1L;

}
