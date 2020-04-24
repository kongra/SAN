package san.pcoas;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public TestServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request
      , HttpServletResponse response) throws ServletException, IOException {    
    response.getWriter().append("Served at: ").append(request.getContextPath());
  }

}
