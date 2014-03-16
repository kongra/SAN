package eshop.profile;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeKey implements Serializable {

  @Basic
  private long deptId;

  @Basic
  private long empId;

  public EmployeeKey() {
  }

  public EmployeeKey(long deptId, long empId) {
    this.deptId = deptId;
    this.empId = empId;
  }

  @Override
  public final int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (deptId ^ (deptId >>> 32));
    result = prime * result + (int) (empId ^ (empId >>> 32));
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
    if (!(obj instanceof EmployeeKey)) {
      return false;
    }
    EmployeeKey other = (EmployeeKey) obj;
    if (deptId != other.deptId) {
      return false;
    }
    if (empId != other.empId) {
      return false;
    }
    return true;
  }

}
