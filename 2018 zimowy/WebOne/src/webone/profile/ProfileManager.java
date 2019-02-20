package webone.profile;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class ProfileManager {

  @PersistenceContext(name = "WebOne")
  private EntityManager em;

  public Profile register(String login, String passwd, String city,
      String street, String postalCode, String number) {
    if (profileExists(login)) {
      return null;
    }
    
    Address addr = findAddress(city, street, postalCode, number);
    if (addr == null) {
      addr = createAddress(city, street, postalCode, number);
    }
    
    Profile p = new Profile();
    p.setLogin(login);
    p.setPasswd(passwd);
    p.setAddress(addr);
    em.persist(p);
    return p;
  }

  public Address createAddress(String city, String street, String postalCode,
      String number) {
    Address a = new Address(city, street, postalCode, number);
    em.persist(a);
    return a;
  }

  public Address findAddress(String city, String street, String postalCode,
      String number) {
    Query q =
        em.createQuery("Select a from Address a where a.city = :city and "
            + "a.street = :street and a.postalCode = :postalCode and number = :number");
    q.setParameter("city", city);
    q.setParameter("street", street);
    q.setParameter("postalCode", postalCode);
    q.setParameter("number", number);
    List<Address> addresses = q.getResultList();
    return addresses.isEmpty() ? null : addresses.get(0);
  }

  public boolean profileExists(String login) {
    Query q =
        em.createQuery("Select p.id from Profile p where p.login = :login");
    q.setParameter("login", login);
    @SuppressWarnings("unchecked")
    List<Long> ids = q.getResultList();
    return !ids.isEmpty();
  }

  public Profile findProfile(String login) {
    Query q = em.createQuery("Select p from Profile p where p.login = :login");
    q.setParameter("login", login);
    @SuppressWarnings("unchecked")
    List<Profile> profiles = q.getResultList();
    if (profiles.isEmpty()) {
      return null;
    }
    return profiles.get(0);
  }

  public Profile findProfile(String login, String passwd) {
    Profile p = findProfile(login);
    if (p == null) {
      return null;
    }
    return passwd.equals(p.getPasswd()) ? p : null;
  }

}
