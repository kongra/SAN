package ewus;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("D")
// @Table(name = "DOCTORS")
@NamedQuery(name = "findDoctorsOfSpec", 
            query = "Select doc from Doctor doc where doc.spec = :spec")
public class Doctor extends Employee {

  private String spec;

  public String getSpec() {
    return spec;
  }

  public void setSpec(String spec) {
    this.spec = spec;
  }

}
