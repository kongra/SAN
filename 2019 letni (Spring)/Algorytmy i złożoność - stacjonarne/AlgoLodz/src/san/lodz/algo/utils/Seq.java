package san.lodz.algo.utils;

public interface Seq<T> {

  T first();

  Seq<T> rest();

  boolean isEmpty();

  default String asString() {
    StringBuilder buf = new StringBuilder("(");
    Seq<T> s = this;
    while(!s.isEmpty()) {
      buf.append(s.first());
      s = s.rest();
      if (!s.isEmpty()) {
        buf.append(",");
      }
    }

    return buf.append(")").toString();
  }

}
