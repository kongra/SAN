package san.profile;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import san.config.Config;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "login", urlPatterns = {
  "/profile/login"
})
public class Login extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Inject
  private Config config;

  @Inject
  private ProfileManager profileManager;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public Login() {
    super();
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String login = request.getParameter("login");
    String passwd = request.getParameter("passwd");

    System.out.println(config.getValue());

    Long id = profileManager.validateProfile(login, passwd);
    if (id != null) {
      // SUCCESS
      request.getSession().setAttribute(ProfileManager.UID, id);
      response.sendRedirect("../private.jsp");
    }
    else {
      // FAILURE
      response.sendRedirect("../loginform.jsp");
    }
  }

}
