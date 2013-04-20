package esklep.profile;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "profile/login", urlPatterns = {
  "/profile/login"
})
public class LoginServlet extends HttpServlet {

  public static final String LOGGED_IN = "logged-in";

  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public LoginServlet() {
    super();
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String nick = request.getParameter("nick");
    String pass = request.getParameter("pass");

    if (isValidUser(nick, pass)) {
      request.getSession().setAttribute(LOGGED_IN, true);
      response.sendRedirect("/ESklep/index.html");
    }
    else {
      response.sendRedirect("/ESklep/loginform.html");
    }

  }

  private boolean isValidUser(String nick, String pass) {
    return Profile.authenticate(nick, pass) != null;
  }

}
