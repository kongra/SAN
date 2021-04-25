package san.jee1.profile;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import san.jee1.Utils;

@WebServlet("/profile/signin")
public final class Signin extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public Signin() {
    super();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email");
    email = email == null ? "" : email;
    email = email.trim();
    email = email.toLowerCase();

    String passwd = request.getParameter("passwd");
    passwd = passwd == null ? "" : passwd;

    if (ProfileTools.isRegistered(email, passwd)) {
      // Auth. OK
      request.getSession().setAttribute("email", email);
      response.sendRedirect(Utils.url("/index.jsp"));
      return;
    }

    // Auth. ERR
    request.setAttribute("error", "Illegal email and/or password");
    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profile/signin.jsp");
    dispatcher.forward(request, response);
  }

}
