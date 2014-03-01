package san.profile;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProfileTools implements LocalProfileTools {

  @PersistenceContext(unitName = "Dziekanat")
  private EntityManager em;

  public ProfileTools() {
    ;
  }

  @Override
  public Profile findProfile(long id) {
    return em.find(Profile.class, id);
  }

  @Override
  public void createProfile(Profile profile) {
    em.persist(profile);
  }

}
