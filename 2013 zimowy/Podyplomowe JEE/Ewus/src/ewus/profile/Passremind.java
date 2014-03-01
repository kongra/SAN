package ewus.profile;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/profile/Passremind")
public class Passremind extends HttpServlet {

  @EJB
  private RemoteProfileTools profileTools;

  public Passremind() {
    super();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String login = request.getParameter("login");
    if (!StringUtils.isBlank(login)) {
      Future<Profile> result = profileTools.remindPassword(login);
      Profile profile = null;
      try {
        profile = result.get();
      }
      catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      }

      if (profile != null) {
        response.sendRedirect("./loginform.jsp");
        return;
      }
    }

    response.sendRedirect("./passremind.jsp");
  }

  private static final long serialVersionUID = 1L;
}
