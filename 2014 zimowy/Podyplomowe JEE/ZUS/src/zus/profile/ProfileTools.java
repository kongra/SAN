package zus.profile;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import zus.money.Money;

@Stateless
public class ProfileTools {

  @PersistenceContext(unitName = "ZUS")
  private EntityManager em;

  /**
   * @param login
   * @return profile with the given login, if exists, null otwerwise.
   */
  public Profile findProfileByLogin(String login) {
    Query q = em.createNamedQuery("findProfileByLogin");
    q.setParameter("login", login);

    @SuppressWarnings("unchecked")
    final List<Profile> profiles = q.getResultList();
    if (profiles.isEmpty()) {
      return null;
    }
    return profiles.get(0);
  }

  public Long findProfileIdByLogin(String login) {
    Query q = em.createNamedQuery("findProfileIdByLogin");
    q.setParameter("login", login);

    @SuppressWarnings("unchecked")
    final List<Long> ids = q.getResultList();
    if (ids.isEmpty()) {
      return null;
    }
    return ids.get(0);
  }

  public boolean profileExists(String login) {
    return null != findProfileIdByLogin(login);
  }

  /**
   * @param login
   * @param passwd
   * @param firstName
   * @param lastName
   * @return newly created profile unless one with login already exists.
   */
  public Profile createProfile(String login, String passwd, String firstName,
      String lastName, Money contrib) {

    if (profileExists(login)) {
      return null;
    }

    Profile p = new Profile();
    p.setLogin(login);
    p.setPasswd(passwd);
    p.setFirstName(firstName);
    p.setLastName(lastName);
    p.setContrib(contrib);

    em.persist(p);
    return p;
  }

  public void setContrib(Profile p, Money contrib) {
    p.setContrib(contrib);
    em.merge(p);
  }

  public Employee createEmployee(String login, String passwd, String firstName,
      String lastName, String dept, Money contrib) {
    
    if (profileExists(login)) {
      return null;
    }

    Employee e = new Employee();
    e.setLogin(login);
    e.setPasswd(passwd);
    e.setFirstName(firstName);
    e.setLastName(lastName);
    e.setDept(dept);
    e.setContrib(contrib);

    em.persist(e);
    return e;
  }
}
