package san.profile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@WebServlet("/profile/Login")
public class Login extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @PersistenceUnit
  private EntityManagerFactory emf;

  public Login() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    UserTransaction transaction = null;

    try {
      transaction =
          (UserTransaction) new InitialContext()
              .lookup("java:comp/UserTransaction");
    }
    catch (NamingException e) {
      throw new ServletException(e);
    }

    try {
      transaction.begin();
    }
    catch (NotSupportedException | SystemException e) {
      throw new ServletException(e);
    }

    EntityManager emanager = emf.createEntityManager();

    User zbigniewek = new User();
    zbigniewek.setLogin("zbigniewek");
    emanager.persist(zbigniewek);

    
    TypedQuery<User> query = emanager.createNamedQuery("User.findAll", User.class);
    query.setFirstResult(2);
    query.setMaxResults(2);
    
    List<User> results = query.getResultList();

    emanager.close();
    try {
      transaction.commit();
    }
    catch (SecurityException | IllegalStateException | RollbackException
        | HeuristicMixedException | HeuristicRollbackException
        | SystemException e) {
      throw new ServletException(e);
    }

    PrintWriter out = response.getWriter();

    for (User user : results) {
      out.println("*" + user.getId() + " " + user.getLogin());
    }

  }

}
