package zus.test;

import java.io.IOException;
import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zus.money.Currency;
import zus.money.Money;
import zus.profile.Profile;
import zus.profile.ProfileTools;

@WebServlet("/SetContrib")
public class SetContrib extends HttpServlet {

  @EJB
  private ProfileTools profileTools;

  private Profile profile;

  public SetContrib() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    synchronized (this) {
      if (profile == null) {
        System.out.println("SetContrib: wczytuję profil jan.");
        profile = profileTools.findProfileByLogin("jan");
      }
      if (profileTools.setContrib(profile, new Money(Currency.PLN,
          new BigDecimal("1000.0")))) {
        System.out.println("SetContrib: zmodyfikowano contrib.");
      }
      else {
        System.out.println("SetContrib: redirect: ERROR PAGE");
      }
    }

    
  }

}
