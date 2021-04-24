package san.jee1.profile;

import java.io.IOException;

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
    System.out.println("/profile/signing got hit");
    response.sendRedirect(Utils.url("/index.html"));
  }

}
