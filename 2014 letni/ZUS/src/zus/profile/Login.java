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

@WebServlet("/profile/Login")
public class Login extends HttpServlet {

  @PersistenceUnit(unitName = "ZUS")
  private EntityManagerFactory emFactory;

  public Login() {
    super();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String login = request.getParameter("login");
    String password = request.getParameter("password");

    if (StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
      response.sendRedirect("./login.jsp");
      return;
    }

    login = StringUtils.trim(login);
    password = StringUtils.trim(password);

    EntityManager em = null;
    boolean rollBack = false;

    try {
      em = emFactory.createEntityManager();
      em.getTransaction().begin();

      Profile profile = ProfileTools.findProfile(em, login);
      if (profile == null) {
        response.sendRedirect("./login.jsp");
        return;
      }

      if (!password.equals(profile.getPassword())) {
        response.sendRedirect("./login.jsp");
        return;
      }

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
