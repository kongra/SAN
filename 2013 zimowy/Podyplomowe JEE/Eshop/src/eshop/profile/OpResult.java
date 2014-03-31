package eshop.profile;

public class OpResult {

  public static OpResult lockLogout() {
    return new OpResult(OpStatus.LOCK_LOGOUT, null);
  }

  public static OpResult ok(Profile profile) {
    return new OpResult(OpStatus.OK, profile);
  }

  public static OpResult error(Profile profile) {
    return new OpResult(OpStatus.ERROR, null);
  }

  public final OpStatus status;

  public final Profile profile;

  private OpResult(OpStatus status, Profile profile) {
    this.status = status;
    this.profile = profile;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((profile == null) ? 0 : profile.hashCode());
    result = prime * result + ((status == null) ? 0 : status.hashCode());
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
    OpResult other = (OpResult) obj;
    if (profile == null) {
      if (other.profile != null) {
        return false;
      }
    }
    else if (!profile.equals(other.profile)) {
      return false;
    }
    if (status != other.status) {
      return false;
    }
    return true;
  }

}
