package ewus.profile;

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

  @EJB
  private ProfileTools profileTools;

  @EJB
  private Counter counter;

  @EJB
  private GlobalCache cache;

  public Login() {
    super();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    long count = counter.incrementAndGet();
    System.out.println("Count: " + count);
    System.out.println("W keszu: " + cache.get("count", 0));
    cache.put("count", count);

    if (count == 3) {
      counter.remove();
    }

    String login = request.getParameter("login");
    String password = request.getParameter("password");

    if (!StringUtils.isBlank(login) && !StringUtils.isBlank(password)) {
      Profile profile = profileTools.findProfile(login, password);
      if (profile != null) {
        response.sendRedirect("../");
        return;
      }
    }

    response.sendRedirect("./loginform.jsp");
  }

  private static final long serialVersionUID = 1L;
}
