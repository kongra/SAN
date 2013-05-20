package san.jipp.util;

public class LinkedSeq<T> implements Seq<T> {

  @SuppressWarnings("unchecked")
  public static <S, T extends S> Seq<S> withElements(T... elements) {
    Seq<S> result = EMPTY;
    for (int i = elements.length - 1; i >= 0; i--) {
      result = result.addToFront(elements[i]);
    }
    return result;
  }

  private static final Seq EMPTY = new Seq() {

    @Override
    public Seq rest() {
      return this;
    }

    @Override
    public Object first() {
      return null;
    }

    @SuppressWarnings( {
        "synthetic-access", "unchecked"
    })
    @Override
    public Seq addToFront(Object obj) {
      return new LinkedSeq(obj, this);
    }

    @Override
    public boolean isEmpty() {
      return true;
    }

  };

  private final T first;

  private final Seq<T> rest;

  private LinkedSeq(T first, Seq<T> rest) {
    this.first = first;
    this.rest = rest;
  }

  @Override
  public T first() {
    return this.first;
  }

  @Override
  public Seq<T> rest() {
    return this.rest;
  }

  @Override
  public Seq addToFront(T obj) {
    return new LinkedSeq<T>(obj, this);
  }

  @Override
  public boolean isEmpty() {
    return false;
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
