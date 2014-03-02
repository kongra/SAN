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

@WebServlet("/profile/Login")
public class Login extends HttpServlet {
  
  @PersistenceUnit(unitName="Eshop")
  private EntityManagerFactory emFactory;

  public Login() {
    super();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    String login = request.getParameter("login");
    String password = request.getParameter("password");
    
    if(StringUtils.isBlank(login) || StringUtils.isBlank(password)) {
      response.sendRedirect("./loginform.jsp");
      return;
    }
    
    System.out.println("Tworzymy profil ...");
    
    Profile user = new Profile();
    user.setId(1);
    user.setLogin("adam");
    user.setPassword("12345");
    user.setFirstName("Adam");
    user.setLastName("Kowalski");
    
    EntityManager em = null;
    try {
      em = emFactory.createEntityManager();
      em.getTransaction().begin();
      
      em.persist(user);
      
      em.getTransaction().commit();
    }
    catch(Throwable t) {
      t.printStackTrace(System.err);
    }
    finally {
      if(em != null) {
        em.close();
      }
    }
    
    response.sendRedirect("../");
  }

  private static final long serialVersionUID = 1L;
}
