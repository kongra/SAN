package san.jee1.profile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/profile/SignIn")
public final class SignIn extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private static final Map<String, String> credentials;

  static {
    credentials = new HashMap<>();
    credentials.put("kongra@gmail.com", "12345");
    credentials.put("kgrzanek@san.edu.pl", "54321");
  }

  public SignIn() {
    super();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    var passwd1 = credentials.get(email);
    if (passwd.equals(passwd1)) {
      var session = request.getSession();
      session.setAttribute("email", email);
      gotoIndex(response);
    } else {
      localErr(request, response, "Illegal Email or Password");
    }
  }

  public static void gotoIndex(HttpServletResponse response) throws IOException {
    response.sendRedirect("/JEE1_dzienne_lab/index.jsp");
  }

  private void localErr(HttpServletRequest request, HttpServletResponse response, String message)
      throws ServletException, IOException {
    getServletContext().getRequestDispatcher("/signin.jsp").forward(request, response);
  }

}
