package zus.org;

import java.io.Serializable;

public final class DeptKey implements Serializable {

  private long id;
  
  private long companyId;

  public DeptKey () {
  }
  
  public DeptKey(long id, long companyId) {
    this.id = id;
    this.companyId = companyId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (companyId ^ (companyId >>> 32));
    result = prime * result + (int) (id ^ (id >>> 32));
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    DeptKey other = (DeptKey) obj;
    if (companyId != other.companyId) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    return true;
  }
  
  
}
