package san.profile;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import san.jdbc.JDBC;
import san.util.Body;
import san.util.Doclean;

@WebServlet("/profile/AllUsers")
public final class AllUsers extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Inject
  @Named("java:comp/env/jdbc/JEE2")
  private DataSource pool;

  public AllUsers() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html; charset=UTF-8");
    response.setCharacterEncoding("UTF-8");

    final PrintWriter out = response.getWriter();
    // out.println("Test ąęśćółżźń");

    Doclean.run(new Runnable() {
      @Override
      public void run() {
        JDBC.withConnection(pool, new Runnable() {
          @Override
          public void run() {
            JDBC.withQueryResults("select * from users", new Body() {
              @Override
              public void run() throws Break, Continue {
                out.println(JDBC.get("id") + " " + JDBC.get("login") + " "
                    + JDBC.get("passwd") + "<br/>");
              }
            });
          }
        });
      }
    });

    out.flush();
    out.close();
  }

}
