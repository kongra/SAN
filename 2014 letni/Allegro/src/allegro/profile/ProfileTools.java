package allegro.profile;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import allegro.util.Coll;

@Stateless
public class ProfileTools {

  @PersistenceContext(unitName = "Allegro")
  private EntityManager em;

  @SuppressWarnings("unchecked")
  public Profile findProfile(String login) {
    Query query = em.createNamedQuery("findProfileByLogin");
    query.setParameter("login", login);

    return Coll.first((List<Profile>) query.getResultList(), null);
  }

  @SuppressWarnings("unchecked")
  public Long findProfileId(String login) {
    Query query =
        em.createQuery("Select p.id from Profile p where p.login = :login");
    query.setParameter("login", login);

    return Coll.first((List<Long>) query.getResultList(), null);
  }

  public Profile register(String login, String password, String firstName,
      String lastName) {

    Long existingProfileId = findProfileId(login);
    if (existingProfileId != null) {
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

  public Profile authenticate(String login, String password) {
    Profile profile = findProfile(login);
    if (profile == null) {
      return null;
    }
    if (profile.getPassword().equals(password)) {
      return profile;
    }
    return null;
  }

  public Profile changePassword(Profile profile, String oldPassword,
      String newPassword) {
    profile = em.merge(profile);
    if (!profile.getPassword().equals(oldPassword)) {
      return null;
    }
    profile.setPassword(newPassword);
    return profile;
  }

}
