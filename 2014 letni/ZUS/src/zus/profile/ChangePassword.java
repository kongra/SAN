package zus.profile;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/profile/ChangePassword")
public class ChangePassword extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @EJB
  private ProfileTools profileTools;

  public ChangePassword() {
    super();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Profile profile = (Profile) request.getSession().getAttribute(Profile.TAG);
    if (null == profile) {
      System.out.println("Brak profilu w sesji HTTP");

      response.sendRedirect("./changepass.jsp");
      return;
    }

    String oldPassword = request.getParameter("oldPassword");
    String newPassword = request.getParameter("newPassword");

    if (StringUtils.isBlank(oldPassword) || StringUtils.isBlank(newPassword)) {
      System.out.println("oldPassword lub newPassword blank");

      response.sendRedirect("./changepass.jsp");
      return;
    }

    oldPassword = StringUtils.trim(oldPassword);
    newPassword = StringUtils.trim(newPassword);

    profile = profileTools.changePassword(profile, oldPassword, newPassword);

    if (profile != null) {
      request.getSession().setAttribute(Profile.TAG, profile);
      response.sendRedirect("../index.jsp");
    }
    else {
      response.sendRedirect("./changepass.jsp");
    }

  }
}
