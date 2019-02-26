package webone.profile;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/profile/Login")
public final class Login extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @EJB
  private ProfileManager pm;

  @Override
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {

    String login = request.getParameter("login");
    String passwd = request.getParameter("passwd");

    if (pm.findProfile(login, passwd) != null) {
      HttpSession sess = request.getSession(true);
      sess.setAttribute("LOGGED-IN", Boolean.TRUE);
      sess.setAttribute("LOGIN", login);
      response.sendRedirect("../protected.jsp");
      return;
    }

    response.sendRedirect("loginform.jsp");
  }

  private static String MD5(String md5) {
    try {
      java.security.MessageDigest md =
          java.security.MessageDigest.getInstance("MD5");
      byte[] array = md.digest(md5.getBytes());
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < array.length; ++i) {
        sb.append(
          Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
      }
      return sb.toString();
    }
    catch (java.security.NoSuchAlgorithmException e) {
    }
    return null;
  }

}
