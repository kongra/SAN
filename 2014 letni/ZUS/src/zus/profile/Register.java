package zus.profile;

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

  @PersistenceUnit(unitName = "ZUS")
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

    login = StringUtils.trim(login);
    password = StringUtils.trim(password);

    String firstName = StringUtils.trim(request.getParameter("firstName"));
    String lastName = StringUtils.trim(request.getParameter("lastName"));

    EntityManager em = null;
    boolean rollBack = false;

    try {
      em = emFactory.createEntityManager();
      em.getTransaction().begin();

      Long existingId = ProfileTools.findProfileId(em, login);
      if (existingId != null) {
        response.sendRedirect("./register.jsp");
        return;
      }

      Profile profile = new Profile();
      profile.setId(1);
      profile.setLogin(login);
      profile.setPassword(password);
      profile.setFirstName(firstName);
      profile.setLastName(lastName);

      em.persist(profile);

      request.getSession().setAttribute(Profile.TAG, profile);
      response.sendRedirect("../index.jsp");
    }
    catch (RuntimeException e) {
      rollBack = true;
      e.printStackTrace(System.err);
    }
    finally {
      if (em != null) {
        if (rollBack) {
          em.getTransaction().rollback();
        }
        else {
          em.getTransaction().commit();
        }

        em.close();
      }
    }

  }

  private static final long serialVersionUID = 1L;
}
