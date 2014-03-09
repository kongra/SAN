package allegro.profile;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESSES")
public class Address {

  @Id
  private long id;

  private String city;

  private String code;

  private String street;

  private String number;

  @OneToMany
  private Set<Profile> owners;

}
