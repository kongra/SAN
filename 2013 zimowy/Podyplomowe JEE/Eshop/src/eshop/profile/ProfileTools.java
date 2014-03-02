package eshop.profile;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class ProfileTools {

  public static Profile findProfile(EntityManager em, String login) {
    Query query = em.createNamedQuery("findProfileByLogin");
    query.setParameter("login", login);
    
    List profiles = query.getResultList();
    if(profiles.isEmpty()) {
      return null;
    }
    
    return (Profile) profiles.get(0);
  }
}
