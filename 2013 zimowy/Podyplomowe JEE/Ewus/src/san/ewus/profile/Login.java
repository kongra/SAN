package san.ewus.profile;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class Login
 */
@WebServlet("/profile/Login")
public final class Login extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Inject @Named("java:comp/env/jdbc/JEE1")
  private DataSource dataSource;

  public Login() {
    super();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String login = request.getParameter("login");
    String pass = request.getParameter("pass");

    if (Profile.isValid(dataSource, login, pass)) {
      request.getSession().setAttribute(Profile.LOGGEDIN, true);
      response.sendRedirect("/Ewus/index.jsp");
      return;
    }

    response.sendRedirect("loginform.jsp");
  }

}
