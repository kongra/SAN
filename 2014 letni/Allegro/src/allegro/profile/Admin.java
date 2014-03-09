package allegro.profile;

import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("Admin")
public class Admin extends Profile {

  @Basic
  @Enumerated(EnumType.ORDINAL)
  private Scope scope;

}
