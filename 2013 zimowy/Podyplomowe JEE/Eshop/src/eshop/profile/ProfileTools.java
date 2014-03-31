package eshop.profile;

import java.util.Arrays;
import java.util.HashSet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import eshop.currencies.Currency;
import eshop.currencies.Money;
import eshop.utils.Coll;

@Stateless
public class ProfileTools {

  @PersistenceContext(unitName = "Eshop")
  private EntityManager em;

  public B2C registerB2C(String login, String password, String firstName,
      String lastName, Address address, Gender gender) {

    if (null != findProfileID(login)) {
      return null;
    }

    B2C user = new B2C();
    user.setLogin(login);
    user.setPassword(password);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    user.setGender(gender);

    user.setAddress(address);
    address.setB2cs(new HashSet<>(Arrays.asList(user)));

    updateRecentLogTime(user);

    em.persist(address);
    em.persist(user);

    return user;
  }

  @SuppressWarnings("unchecked")
  public Long findProfileID(String login) {
    Query query = em.createNamedQuery("findProfileIDByLogin");
    query.setParameter("login", login);
    return Coll.first(query.getResultList(), null);
  }

  @SuppressWarnings("unchecked")
  public Profile findProfile(String login) {
    Query query = em.createNamedQuery("findProfileByLogin");
    query.setParameter("login", login);
    return Coll.first(query.getResultList(), null);
  }

  public Profile authenticate(String login, String password) {
    Profile profile = findProfile(login);
    if (null == profile) {
      return null;
    }

    if (profile.getPassword().equals(password)) {
      updateRecentLogTime(profile);
      
      Money recentSalary = profile.getSalary();
      Money newSalary;
      if(recentSalary == null) {
        newSalary = Money.of(100, Currency.PLN);
      }
      else {
        System.out.println("recentSalary " + recentSalary);
        newSalary = Money.of(100, Currency.PLN).add(recentSalary);
      }
      profile.setSalary(newSalary);
      
      return profile;
    }
    return null;
  }

  public static void updateRecentLogTime(Profile profile) {
    profile.setRecentLogTime(DateTime.now());
  }

  public OpResult changePassword(HttpSession session, Profile profile,
      String oldPassword, String newPassword) {

    profile = mergeProfile(session, profile);
    if (profile == null) {
      return OpResult.lockLogout();
    }

    if (!profile.getPassword().equals(oldPassword)) {
      System.out.println("Niezgodność oldPass i profile.pass");
      return OpResult.error(profile);
    }
    profile.setPassword(newPassword);

    System.out.println("Hasło zmienione");
    return OpResult.ok(profile);
  }

  public Profile mergeProfile(HttpSession session, Profile profile) {
    try {
      profile = em.merge(profile);
    }
    catch (OptimisticLockException ole) {
      Logout.logout(session);
      System.err.println(ole);
      return null;
    }
    return profile;
  }

}
