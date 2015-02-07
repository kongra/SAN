package zus.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import zus.profile.Profile;
import zus.profile.ProfileTools;

@WebServlet("/test/servlet")
public class TestServlet extends HttpServlet {

  @EJB
  private ProfileTools profileTools;
  
  public TestServlet() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html; charset=utf-8");
    response.setCharacterEncoding("UTF-8");

    try (PrintWriter out = response.getWriter()) {
      out.println("<h1>ąęśćółżźń</h1>");
    }
   
    Profile p = profileTools.createProfile("jan", "1234", "Jan", "Kowalski");
    if(p == null) {
      System.out.println("Utworzenie nie powiodło się.");
    }
    else {
      System.out.println("Utworzono konto użytkownika.");
    }
    
  }

  private static final long serialVersionUID = 1L;
}
