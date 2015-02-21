package zus.profile;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import zus.money.Money;
import zus.org.Dept;
import zus.org.DeptKey;

@Stateless
public class ProfileTools {

  @PersistenceContext(unitName = "ZUS")
  private EntityManager em;

  public Dept findDept() {
    return em.find(Dept.class, new DeptKey(0, 0));
  }

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

  public boolean setContrib(Profile p, Money contrib) {
    p.setContrib(contrib);
    try {
      em.merge(p);
      return true;
    }
    catch (OptimisticLockException e) {
      return false;
    }
  }

  // public void addContrib(Profile p, Money delta) {
  // p = em.merge(p);
  // Money c = p.getContrib();
  // p.setContrib(c.add(delta));
  // }

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

  // @TransactionAttribute(TransactionAttributeType.MANDATORY)
  // public void addDoc(Client c, Doc d) {
  // Set<Doc> docs = c.getDocs();
  // if(docs != null) {
  // docs.add(d);
  // }
  // else {
  // c.setDocs(new HashSet<>(Arrays.asList(d)));
  // }
  // d.setClient(c);
  // // em.merge(c);
  // // em.merge(d);
  // }
}
