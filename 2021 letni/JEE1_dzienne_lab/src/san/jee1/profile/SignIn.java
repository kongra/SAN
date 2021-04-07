package san.jee1.profile;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    System.out.println("Initializing Postgres...");
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println("Done.");
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

    testPostgresConnection1();

    var passwd1 = credentials.get(email);
    if (passwd.equals(passwd1)) {
      var session = request.getSession();
      session.setAttribute("email", email);
      gotoIndex(response);
    } else {
      localErr(request, response, "Illegal Email or Password");
    }
  }

  private static void testPostgresConnection() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:postgresql://localhost/MAAS", "jee", "jee");
      System.out.println("Connection is " + conn);

      Statement stmt = null;
      ResultSet rs = null;
      try {
        stmt = conn.createStatement();
        rs = stmt.executeQuery("select id, email from profiles");

        while (rs.next()) {
          System.out.println(rs.getLong("id"));
          System.out.println(rs.getString("email"));
        }

      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (rs != null) {
          rs.close();
        }
        if (stmt != null) {
          stmt.close();
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }

  private static void testPostgresConnection1() {
    try {
      try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost/MAAS", "jee", "jee");
          Statement stmt = conn.createStatement();
          ResultSet rs = stmt.executeQuery("select id, email from profiles")) {
        while (rs.next()) {
          System.out.println(rs.getLong("id"));
          System.out.println(rs.getString("email"));
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
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
