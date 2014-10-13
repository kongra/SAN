package allegro.profile;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue("B2C")
public class B2C extends Profile {

  @Enumerated(EnumType.STRING)
  private Gender gender;
  
  public B2C() {
    ;
  }

  public B2C(Gender gender) {
    this.gender = gender;
  }

  public Gender gender() {
    return gender;
  }

}
