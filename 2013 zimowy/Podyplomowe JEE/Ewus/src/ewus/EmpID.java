package ewus;

import java.io.Serializable;

public class EmpID implements Serializable {

  private long deptId;

  private long id;

  public EmpID() {
    ;
  }

  public EmpID(long deptId, long id) {
    this.deptId = deptId;
    this.id = id;
  }

  @Override
  public final int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (deptId ^ (deptId >>> 32));
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
    if (!(obj instanceof EmpID)) {
      return false;
    }
    EmpID other = (EmpID) obj;
    if (deptId != other.deptId) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    return true;
  }

}
