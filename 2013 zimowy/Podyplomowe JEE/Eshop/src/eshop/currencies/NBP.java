package eshop.currencies;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/currencies/NBP")
public class NBP extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  @EJB
  private NBPParser parser;
  
  public NBP() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    parser.loadCurrencyRates();
    System.out.println("Sukces");
    
  }

}
