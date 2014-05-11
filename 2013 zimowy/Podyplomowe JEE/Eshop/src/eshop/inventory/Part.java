package eshop.inventory;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "PARTS")
@NamedQueries({
  @NamedQuery(name = "findPartByCode", query = "Select p from Part p where p.code = :code")
})
public class Part {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTS-GEN")
  @SequenceGenerator(name = "PARTS-GEN", allocationSize = 100, sequenceName = "PARTS_SEQ")
  private long id;

  @Version
  private long version;

  @ManyToOne
  private InventoryPart type;

  @Column(nullable = false, unique = true)
  private String code;

  @ManyToMany
  private Set<Part> subparts;

  public Part() {
  }

  public Part(InventoryPart type, String code) {
    this.type = type;
    this.code = code;
  }

  public Part(InventoryPart type, String code, Set<Part> subparts) {
    this.type = type;
    this.code = code;
    this.subparts = subparts;
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
    if (!(obj instanceof Part)) {
      return false;
    }
    Part other = (Part) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

  
}
