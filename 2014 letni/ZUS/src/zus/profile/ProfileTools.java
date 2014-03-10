package zus.profile;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class ProfileTools {

  public static Profile findProfile(EntityManager em, String login) {
    Query query = em.createNamedQuery("findProfileByLogin");
    query.setParameter("login", login);
    List results = query.getResultList();
    if (results.isEmpty()) {
      return null;
    }
    return (Profile) results.get(0);
  }

  public static Long findProfileId(EntityManager em, String login) {
    Query query = em.createNamedQuery("findProfileIdByLogin");
    query.setParameter("login", login);

    try {
      return (Long) query.getSingleResult();
    }
    catch (NoResultException e) {
      return null;
    }
  }

}
