package allegro.finance;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/finance/NBPServlet")
public class NBPServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @EJB
  private MoneyTools moneyTools;
  
  public NBPServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    if(moneyTools.updateCurrencyRates()) {
      System.out.println("Waluty zaktualizowano!");
    }
    else {
      System.out.println("Aktualizacja nie została przeprowadzona.");
    }
    
  }

}
