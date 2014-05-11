package eshop.inventory;

import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "PARTTYPES")
@NamedQuery(name = "findInventoryPart", query = "Select ip from InventoryPart ip where ip.name = :name")
public class InventoryPart {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INVPARTS-GEN")
  @SequenceGenerator(name = "INVPARTS-GEN", allocationSize = 100, sequenceName = "INVPARTS_SEQ")
  private long id;

  @Version
  private long version;

  @Column(nullable = false, unique = true)
  private String name;

  @ManyToMany
  private Map<Prop, PropValue> propValues;

  @Column(nullable = false)
  private long amount;

  @ManyToMany
  private Set<InventoryPart> subparts;

  public InventoryPart() {
  }

  public InventoryPart(String name, Map<Prop, PropValue> propValues) {
    this.name = name;
    this.propValues = propValues;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Map<Prop, PropValue> getPropValues() {
    return propValues;
  }

  @Override
  public final int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
  }

  @Override
  public final boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof InventoryPart)) {
      return false;
    }
    InventoryPart other = (InventoryPart) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

  public Set<InventoryPart> getSubparts() {
    return subparts;
  }

  public void setSubparts(Set<InventoryPart> subparts) {
    this.subparts = subparts;
  }

}
