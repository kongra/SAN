package allegro.profile;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/profile/Login")
public class Login extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @EJB
  private ProfileTools profileTools;

  public Login() {
    super();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String login = request.getParameter("login");
    String password = request.getParameter("password");

    if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
      response.sendRedirect("./login.jsp");
      return;
    }

    Profile profile = profileTools.authenticate(login, password);
    if (profile == null) {
      response.sendRedirect("./login.jsp");
      return;
    }

    request.getSession().setAttribute(Profile.TAG, profile);
    response.sendRedirect("../index.jsp");
  }

}
