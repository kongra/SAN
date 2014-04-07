package zus.finance;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/finance/DoImportNBP")
public class DoImportNBP extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @EJB
  private MoneyTools moneyTools;

  public DoImportNBP() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    moneyTools.importNBP();
  }

}
