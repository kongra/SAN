package san.jee;

import java.io.IOException;
import java.sql.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/TestDB")
public class TestDB extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Inject
  @Named("java:comp/env/jdbc/JEE")
  private DataSource ds;

  public TestDB() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try (Connection conn = ds.getConnection()) {
      try (Statement stmt = conn.createStatement()) {
        try (ResultSet rs = stmt.executeQuery("select * from profiles")) {
          while (rs.next()) {
            System.out.println(rs.getString("email"));
          }
        }
      }
    } catch (SQLException e) {
      e.printStackTrace(System.err);
    }
  }

}
