package eshop.currencies.nbp;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/currencies/NBPServlet")
public class NBPServlet extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  @EJB
  private NBPParser parser;
  
  public NBPServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    parser.updateCurrencyRates();
    System.out.println("Sukces");
    
  }

}
