package webone.profile;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/profile/Register")
public final class Register extends HttpServlet {

  @EJB
  private ProfileManager pm;

  @Override
  protected void doPost(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    String login = request.getParameter("login");
    String passwd = request.getParameter("passwd");
    String city = request.getParameter("city");
    String street = request.getParameter("street");
    String postalCode = request.getParameter("postalCode");
    String number = request.getParameter("number");

    Profile profile = pm.register(login, passwd, city, street, postalCode, number);
    if (profile == null) {
      response.sendRedirect("register.jsp");
      return;
    }

    HttpSession sess = request.getSession(true);
    sess.setAttribute("LOGGED-IN", Boolean.TRUE);
    sess.setAttribute("LOGIN", login);
    response.sendRedirect("../index.jsp");
  }

}
