package ewus.profile;

import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.QueryTimeoutException;

@Stateless
public class ProfileTools {

  @PersistenceContext(unitName = "Ewus")
  private EntityManager em;

  @Resource
  private SessionContext ctx;
  
  @EJB
  private Newsletter newsletter;

  @TransactionAttribute(TransactionAttributeType.SUPPORTS)
  public Profile findProfile(String login) {
    Query query = em.createNamedQuery("findProfileByLogin");
    query.setParameter("login", login);

    try {
      newsletter.setup(14, 25, 15);
      newsletter.newSetup(15);
      
      return (Profile) query.getSingleResult();
    }
    catch (NoResultException | QueryTimeoutException e) {
      return null;
    }
    catch (Throwable t) {
      // log
      return null;
    }
  }

  @Interceptors(Println.class)
  @TransactionAttribute(TransactionAttributeType.SUPPORTS)
  public Profile findProfile(String login, String password) {
    Profile profile = findProfile(login);
    if (profile == null) {
      return null;
    }

    if (password.equals(profile.getPassword())) {
      return profile;
    }

    return null;
  }

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Profile register(String login, String password, String firstName,
      String lastName) {

    Profile profile = findProfile(login);
    if (profile != null) {
      return null;
    }

    profile = new Profile();
    profile.setLogin(login);
    profile.setPassword(password);
    profile.setFirstName(firstName);
    profile.setLastName(lastName);

    em.persist(profile);
    return profile;
  }

  @Asynchronous
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Future<Profile> remindPassword(String login) {
    Profile profile = findProfile(login);
    if (profile == null) {
      return new AsyncResult<Profile>(null);
    }

    profile.setPassword("twoje-nowe-haslo-12345");
    em.merge(profile);

    return new AsyncResult<Profile>(profile);
  }

  @PostConstruct
  private void postConstruct() {
    System.out.println("postConstruct() dla ziarna " + this);
  }

  @PreDestroy
  private void preDestroy() {
    System.out.println("preDestroy() dla ziarna " + this);
  }

  // @AroundInvoke
  // private Object println(InvocationContext ic) {
  // System.out.println("Przed wywołaniem...");
  // try {
  // return ic.proceed();
  // }
  // catch (Exception e) {
  // throw new RuntimeException(e);
  // }
  // finally {
  // System.out.println("Po wywołaniu");
  // }
  // }

}
