package ewus.profile;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/profile/Register")
public class Register extends HttpServlet {

  @EJB
  private ProfileTools profileTools;

  public Register() {
    super();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String login = request.getParameter("login");
    String password = request.getParameter("password");
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");

    if (!StringUtils.isBlank(login) && !StringUtils.isBlank(password)) {
      Profile profile =
          profileTools.register(login, password, firstName, lastName);
      if (profile != null) {
        response.sendRedirect("./loginform.jsp");
        return;
      }
    }

    response.sendRedirect("./register.jsp");
  }

  private static final long serialVersionUID = 1L;

}
