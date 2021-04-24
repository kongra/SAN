package san.jee1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet")
public final class TestServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public TestServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    var name = request.getParameter("name");
    if (name == null) name = "";
    name = name.trim();

    response.setContentType("text/html; charset=UTF-8");
    response.setCharacterEncoding("UTF-8");

    try (var writer = response.getWriter()) {
      writer.append("<h1>Witaj ").append(name).append("</h1>");
    }
  }

}
