package san.jipp.util;

public class LinkedSeq implements Seq {

  public static final Seq EMPTY = new Seq() {

    @Override
    public Seq rest() {
      return this;
    }

    @Override
    public Object first() {
      return null;
    }

    @SuppressWarnings("synthetic-access")
    @Override
    public Seq addToFront(Object obj) {
      return new LinkedSeq(obj, this);
    }
  };

  private final Object first;

  private final Seq rest;

  private LinkedSeq(Object first, Seq rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public Seq addToFront(Object obj) {
    return new LinkedSeq(obj, this);
  }

  @Override
  public Object first() {
    return this.first;
  }

  @Override
  public Seq rest() {
    return this.rest;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder("(");

    Seq s = this;
    while (s != EMPTY) {
      buf.append(s.first());
      s = s.rest();
      if (s != EMPTY) {
        buf.append(" ");
      }
    }

    return buf.append(")").toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((first() == null) ? 0 : first().hashCode());
    result = prime * result + ((rest() == null) ? 0 : rest().hashCode());
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
    if (!(obj instanceof Seq)) {
      return false;
    }
    Seq other = (Seq) obj;
    if (first() == null) {
      if (other.first() != null) {
        return false;
      }
    }
    else if (!first().equals(other.first())) {
      return false;
    }
    if (rest() == null) {
      if (other.rest() != null) {
        return false;
      }
    }
    else if (!rest().equals(other.rest())) {
      return false;
    }
    return true;
  }
  
}
