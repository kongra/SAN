package eshop.profile;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/profile/Register")
public class Register extends HttpServlet {

  @PersistenceUnit(unitName = "Eshop")
  private EntityManagerFactory emFactory;

  public Register() {
    super();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String login = request.getParameter("login");
    String password = request.getParameter("password");
    String firstName = request.getParameter("firstName");
    String lastName = request.getParameter("lastName");

    if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
      System.out.println("Brak loginu i/lub hasła!!!");
      response.sendRedirect("./register.jsp");
      return;
    }

    EntityManager em = null;
    try {
      em = emFactory.createEntityManager();
      em.getTransaction().begin();

      // em.persist(user);
      Profile profile = ProfileTools.findProfile(em, login);
      if (profile != null) {
        System.out.println("Login już istnieje!!!");
        response.sendRedirect("./register.jsp");
        return;
      }

      Profile user = new Profile();

      user.setLogin(login);
      user.setPassword(password);
      user.setFirstName(firstName);
      user.setLastName(lastName);
      em.persist(user);
      
      System.out.println("Zarejestrowano użytkownika.");
      
      em.getTransaction().commit();
    }
    catch (Throwable t) {
      t.printStackTrace(System.err);
    }
    finally {
      if (em != null) {
        em.close();
      }
    }

    response.sendRedirect("./loginform.jsp");

  }

  private static final long serialVersionUID = 1L;

}
