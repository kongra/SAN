package san.profile;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet(name = "login", urlPatterns = {
  "/profile/login"
})
public class Login extends HttpServlet {
  private static final long serialVersionUID = 1L;

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
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String login = request.getParameter("login");
    String passwd = request.getParameter("passwd");
    
    Long id = Profile.validate(login, passwd);
    if(id != null) {
      // SUCCESS
      request.getSession().setAttribute(Profile.UID, id);
      response.sendRedirect("../private.jsp");
    }
    else {
      // FAILURE
      response.sendRedirect("../loginform.jsp");
    }
  }

}
