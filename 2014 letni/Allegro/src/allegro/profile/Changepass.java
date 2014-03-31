package allegro.profile;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/profile/Changepass")
public class Changepass extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @EJB
  private ProfileTools profileTools;

  public Changepass() {
    super();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String oldPassword = request.getParameter("oldPassword");
    String newPassword = request.getParameter("newPassword");

    if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
      response.sendRedirect("./changepass.jsp");
      return;
    }

    Profile profile = (Profile) request.getSession().getAttribute(Profile.TAG);
    profile = profileTools.changePassword(profile, oldPassword, newPassword);
    if (profile == null) {
      response.sendRedirect("./changepass.jsp");
      return;
    }

    request.getSession().setAttribute(Profile.TAG, profile);
    response.sendRedirect("../index.jsp");
  }

}
