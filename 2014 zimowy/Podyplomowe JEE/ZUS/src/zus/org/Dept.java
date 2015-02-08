package zus.org;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(DeptKey.class)
public class Dept {

  @Id
  private long id;
  
  @Id
  private long companyId;
  
  private String name;
  
}
