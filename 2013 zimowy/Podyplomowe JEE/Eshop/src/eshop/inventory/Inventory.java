package eshop.inventory;

import java.util.Map;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import eshop.utils.Coll;

@Stateless
public class Inventory {

  @PersistenceContext(unitName = "EShop")
  private EntityManager em;

  public PropValue propValue(long value) {
    Query query = em.createNamedQuery("findIntValue");
    query.setParameter("value", value);
    @SuppressWarnings("unchecked")
    PropValue result = Coll.first(query.getResultList(), null);
    if (result != null) {
      return result;
    }

    result = new IntValue(value);
    em.persist(result);
    return result;
  }

  public PropValue propValue(String value) {
    Query query = em.createNamedQuery("findStringValue");
    query.setParameter("value", value);
    @SuppressWarnings("unchecked")
    PropValue result = Coll.first(query.getResultList(), null);
    if (result != null) {
      return result;
    }

    result = new StringValue(value);
    em.persist(result);
    return result;
  }

  public Prop prop(String name) {
    Query query = em.createNamedQuery("findProp");
    query.setParameter("name", name);
    @SuppressWarnings("unchecked")
    Prop result = Coll.first(query.getResultList(), null);
    if (result != null) {
      return result;
    }

    result = new Prop(name);
    em.persist(result);
    return result;
  }

  public InventoryPart inventoryPart(String name, Map<Prop, PropValue> propValues, Set<InventoryPart> subparts) {
    Query query = em.createNamedQuery("findPartType");
    query.setParameter("name", name);
    @SuppressWarnings("unchecked")
    InventoryPart result = Coll.first(query.getResultList(), null);
    if (result != null) {
      return result;
    }

    result = new InventoryPart(name, propValues);
    result.setSubparts(subparts);
    
    em.persist(result);
    return result;
  }

  @SuppressWarnings("unchecked")
  public Part findPart(String code) {
    Query query = em.createNamedQuery("findPartByCode");
    query.setParameter("code", code);
    return Coll.first(query.getResultList(), null);
  }

  public Part createPart(InventoryPart type, String code, Set<Part> subparts) {
    Part p = new Part(type, code, subparts);
    em.persist(p);
    return p;
  }

  public Part createPart(InventoryPart type, String code) {
    Part p = new Part(type, code);
    em.persist(p);
    return p;
  }
}
