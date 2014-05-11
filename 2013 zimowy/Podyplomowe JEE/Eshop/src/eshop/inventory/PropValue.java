package eshop.inventory;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@Entity
@Inheritance
@DiscriminatorColumn(name = "PROPVAL_TYPE")
public abstract class PropValue {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROPVALS-GEN")
  @SequenceGenerator(name = "PROPVALS-GEN", allocationSize = 100, sequenceName = "PROPVALS_SEQ")
  private long id;

  @Version
  private long version;

  public abstract Object value();

  @Override
  public String toString() {
    return String.valueOf(value());
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
    if (!(obj instanceof PropValue)) {
      return false;
    }
    PropValue other = (PropValue) obj;
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
