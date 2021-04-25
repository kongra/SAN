package san.jee1.profile;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import san.jee1.Utils;

@WebServlet("/profile/signout")
public final class Signout extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public Signout() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getSession().invalidate();
    response.sendRedirect(Utils.url("/index.jsp"));
  }

}
