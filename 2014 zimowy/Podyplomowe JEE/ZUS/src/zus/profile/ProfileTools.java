package zus.profile;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

  /**
   * @param login
   * @param passwd
   * @param firstName
   * @param lastName
   * @return newly created profile unless one with login already exists.
   */
  public Profile createProfile(String login, String passwd, String firstName,
      String lastName) {

    Profile existingOne = findProfileByLogin(login);
    if (null != existingOne) {
      return null;
    }

    Profile p = new Profile();
    p.setLogin(login);
    p.setPasswd(passwd);
    p.setFirstName(firstName);
    p.setLastName(lastName);

    em.persist(p);
    return p;
  }

}
