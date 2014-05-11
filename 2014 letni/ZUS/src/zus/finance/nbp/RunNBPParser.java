package zus.finance.nbp;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zus.finance.MoneyTools;

@WebServlet("/finance/RunNBPParser")
public class RunNBPParser extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @EJB
  private MoneyTools moneyTools;

  public RunNBPParser() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    moneyTools.importNBP();
  }

}
