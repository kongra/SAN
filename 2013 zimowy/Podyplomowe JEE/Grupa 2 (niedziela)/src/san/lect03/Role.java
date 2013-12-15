package san.lect03;

public enum Role {

  GUEST("Gość w systemie") {
    @Override
    public int rights() {
      return 0;
    }
  },
  USER("Zwykły użytkownik") {
    @Override
    public int rights() {
      return 1;
    }
  },
  SUPERUSER("Uprzywilejowany") {
    @Override
    public int rights() {
      return 2;
    }
  },
  ROOT("Szef") {
    @Override
    public int rights() {
      return 3;
    }
  };

  private final String desc;

  private Role(String desc) {
    this.desc = desc;
  }

  @Override
  public String toString() {
    return this.desc;
  }

  public abstract int rights();
  
  
}
