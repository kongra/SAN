package allegro.profile;

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

  @PersistenceUnit(unitName = "Allegro")
  private EntityManagerFactory emFactory;

  public Register() {
    super();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String login = request.getParameter("login");
    String password = request.getParameter("password");

    if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
      response.sendRedirect("./register.jsp");
      return;
    }

    EntityManager em = null;
    boolean wasRolledBack = false;
    try {
      em = emFactory.createEntityManager();
      em.getTransaction().begin();

      Long existingProfileId = ProfileTools.findProfileId(em, login);
      if (existingProfileId != null) {
        em.getTransaction().rollback();
        wasRolledBack = true;

        response.sendRedirect("./register.jsp");
        return;
      }

      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");

      Profile profile = new Profile();

      profile.setLogin(login);
      profile.setPassword(password);
      profile.setFirstName(firstName);
      profile.setLastName(lastName);

      em.persist(profile);

      response.sendRedirect("./login.jsp");
    }
    catch (RuntimeException e) {
      e.printStackTrace(System.err);

      wasRolledBack = true;
      if (null != em) {
        em.getTransaction().rollback();
      }
    }
    finally {
      if (null != em) {
        if (!wasRolledBack) {
          em.getTransaction().commit();
        }
        em.close();
      }
    }

  }

  private static final long serialVersionUID = 1L;
}
