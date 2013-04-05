package san.lodz.calc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Quadr
 */
@WebServlet(name = "quadr", description = "Calculates the roots of a quadratic function", urlPatterns = {
  "/quadr"
})
public class Quadr extends HttpServlet {

  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Quadr() {
    super();
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    String pa = request.getParameter("a");
    String pb = request.getParameter("b");
    String pc = request.getParameter("c");
    
    double a = Double.parseDouble(pa);
    double b = Double.parseDouble(pb);
    double c = Double.parseDouble(pc);
    
    double delta = b * b - 4 * a * c;
    double x1 = (-b - Math.sqrt(delta)) / (2 * a);
    double x2 = (-b + Math.sqrt(delta)) / (2 * a);
    
    response.setContentType("text/html; charset=utf-8");
    PrintWriter out = response.getWriter();
    
    out.println("Pierwiastki wielomianu to:<br>");
    out.println("x1: &nbsp;" + x1 + "<br>");
    out.println("x2: &nbsp;" + x2);
  }

}
