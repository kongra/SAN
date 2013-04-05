package esklep.content;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import esklep.profile.LoginServlet;

/**
 * Servlet implementation class SecureContent
 */
@WebServlet(name = "content/secure", urlPatterns = {
  "/content/secure"
})
public class SecureContent extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public SecureContent() {
    super();
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    if (isLoggedIn(request)) {
      response.setContentType("text/html; charset=utf-8");
      PrintWriter out = response.getWriter();

      out.println("Hiper super tajne rzeczy!!!");
    }
    else {
      response.sendRedirect("/ESklep/loginform.html");
    }
  }

  private boolean isLoggedIn(HttpServletRequest request) {
    Object loggedIn = request.getSession().getAttribute(LoginServlet.LOGGED_IN);
    return loggedIn == Boolean.TRUE;
  }

}
