package san.pcoas;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public final class Login extends HttpServlet {
  
  private long totalCount;
  private long successCount;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String login = request.getParameter("login");
    String pass  = request.getParameter("pass");
    
    synchronized (this) {
      totalCount += 1;
    }
    
    if ("root".equals(login) && "root12345".equals(pass)) {
      synchronized (this) {
        successCount += 1;
      }
      
      request.getSession().setAttribute("login", "root");
      response.sendRedirect("index.jsp");
    }
    else {
      response.sendRedirect("login.jsp");
    }
    
    synchronized (this) {
      System.out.println("totalCount=" + totalCount + ", successCount=" + successCount);
    }
    
  }

}
