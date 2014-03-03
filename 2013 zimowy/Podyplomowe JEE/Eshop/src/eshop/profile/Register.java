package eshop.profile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

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
    boolean wasRolledBack = false;

    try {
      em = emFactory.createEntityManager();
      em.getTransaction().begin();

      // em.persist(user);
      Profile profile = ProfileTools.findProfile(em, login);
      if (profile != null) {
        System.out.println("Login już istnieje!!!");
        // em.getTransaction().rollback();
        response.sendRedirect("./register.jsp");
        return;
      }

      Address address = new Address();
      address.setStreet("Tokarzewskiego");
      address.setCity("Łódź");
      address.setCountry("Poland");
      address.setNumber("2a");      

      Profile user = new Profile();
      user.setLogin(login);
      user.setPassword(password);
      user.setFirstName(firstName);
      user.setLastName(lastName);
      
      user.setAddress(address);
      address.setProfiles(new HashSet<>(Arrays.asList(user)));
      
      em.persist(address);
      em.persist(user);

      em.flush();

      System.out.println("Zarejestrowano użytkownika.");
    }
    catch (RuntimeException t) {
      t.printStackTrace(System.err);
      if (em != null) {
        em.getTransaction().rollback();
        wasRolledBack = true;
      }
    }
    finally {
      if (em != null) {
        if (!wasRolledBack) {
          em.getTransaction().commit();
        }
        em.close();
      }
    }

    response.sendRedirect("./loginform.jsp");

  }

  private static final long serialVersionUID = 1L;

}
