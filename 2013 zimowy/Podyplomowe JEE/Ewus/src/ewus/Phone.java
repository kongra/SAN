package ewus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Phone {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHONE_ID_GEN")
  @SequenceGenerator(name = "PHONE_ID_GEN", sequenceName = "PHONE_ID_SEQ")
  private long id;
  
  private String type; // private, official, fax
  
  @ManyToOne
  private Employee owner;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Employee getOwner() {
    return owner;
  }

  public void setOwner(Employee owner) {
    this.owner = owner;
  }

  public long getId() {
    return id;
  }
  
}
