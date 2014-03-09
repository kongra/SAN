package allegro.profile;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class ProfileTools {

  public static Profile findProfile(EntityManager em, String login) {
    Query query = em.createNamedQuery("findProfileByLogin");
    query.setParameter("login", login);
    try {
      return (Profile) query.getSingleResult();
    }
    catch (NoResultException e) {
      return null;
    }
  }

  public static Long findProfileId(EntityManager em, String login) {
    Query query =
        em.createQuery("Select p.id from Profile p where p.login = :login");
    query.setParameter("login", login);
    try {
      return (Long) query.getSingleResult();
    }
    catch (NoResultException e) {
      return null;
    }
  }

}
