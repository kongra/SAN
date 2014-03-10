package zus.profile;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ProfileTools {

  @PersistenceContext(unitName = "ZUS")
  private EntityManager em;

  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public Profile findProfile(String login) {
    Query query = em.createNamedQuery("findProfileByLogin");
    query.setParameter("login", login);
    List results = query.getResultList();
    if (results.isEmpty()) {
      return null;
    }
    return (Profile) results.get(0);
  }

  public Long findProfileId(String login) {
    Query query = em.createNamedQuery("findProfileIdByLogin");
    query.setParameter("login", login);

    try {
      return (Long) query.getSingleResult();
    }
    catch (NoResultException e) {
      return null;
    }
  }

  public Profile authenticate(String login, String password) {
    Profile profile = findProfile(login);
    if (profile != null && profile.getPassword().equals(password)) {
      return profile;
    }
    return null;
  }

  public Profile register(String login, String password, String firstName,
      String lastName) {

    Long id = findProfileId(login);
    if (id != null) {
      return null;
    }

    Profile profile = new Profile();
    profile.setLogin(login);
    profile.setPassword(password);
    profile.setFirstName(firstName);
    profile.setLastName(lastName);

    em.persist(profile);
    return profile;
  }

}
