package san.jee1.profile;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import san.jee1.DBI;

@WebServlet("/profile/SignIn")
public final class SignIn extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Inject
  @Named("java:comp/env/jdbc/MAAS")
  private DataSource dataSource;

  public SignIn() {
    super();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    System.out.println("SignIn.doPost()");

    var email = request.getParameter("email");
    if (null == email) {
      localErr(request, response, "Email is not set");
      return;
    }

    var passwd = request.getParameter("passwd");
    if (null == passwd) {
      localErr(request, response, "Password is not set");
      return;
    }

    email = email.strip();
    if ("".equals(email)) {
      localErr(request, response, "Email is blank");
      return;
    }

    passwd = passwd.strip();
    if ("".equals(passwd)) {
      localErr(request, response, "Password is blank");
      return;
    }

    if (passwd.equals(profilePasswordForEmail(email))) {
      var session = request.getSession();
      session.setAttribute("email", email);
      gotoIndex(response);
    } else {
      localErr(request, response, "Illegal Email or Password");
    }
  }

  private String profilePasswordForEmail(String email) {
    return DBI.selectOne(dataSource, "select passwd from profiles where email='" + email + "'", "passwd");
  }

  public static void gotoIndex(HttpServletResponse response) throws IOException {
    response.sendRedirect("/JEE1_dzienne_lab/index.jsp");
  }

  private void localErr(HttpServletRequest request, HttpServletResponse response, String message)
      throws ServletException, IOException {
    request.setAttribute("localErrMessage", message);
    getServletContext().getRequestDispatcher("/signin.jsp").forward(request, response);
  }

}
