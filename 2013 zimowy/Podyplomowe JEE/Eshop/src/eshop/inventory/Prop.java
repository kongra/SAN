package eshop.inventory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "PROPS")
@NamedQuery(name = "findProp", query = "Select p from Prop p where p.name = :name")
public class Prop {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROPS-GEN")
  @SequenceGenerator(name = "PROPS-GEN", allocationSize = 100, sequenceName = "PROPS_SEQ")
  private long id;

  @Version
  private long version;

  @Column(nullable = false, unique = true)
  private String name;

  public Prop() {
  }

  public Prop(String name) {
    this.name = name;
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
    if (!(obj instanceof Prop)) {
      return false;
    }
    Prop other = (Prop) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

  
}
