package allegro.profile;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("B2B")
public class B2B extends Profile {

  @ManyToMany
  private Set<B2B> contractors;

}
