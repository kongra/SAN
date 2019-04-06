package san.office;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Hello")
public class Hello extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public Hello() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws ServletException, IOException {    
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().append("Served at: ").append(request.getContextPath()).append("ąęśćółżźń");
  }

}
