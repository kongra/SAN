package san.lodz.algo.utils;

import san.lodz.math.Binary;
import san.lodz.math.Unary;

public interface Seq<T> {

  T first();

  Seq<T> rest();

  boolean isEmpty();

  Seq<T> cons(T e);

  default String asString() {
    StringBuilder buf = new StringBuilder("(");
    this.forEach((e, isLast) -> {
      buf.append(e);
      if (!isLast) {
        buf.append(",");
      }
      return null;
    });
    return buf.append(")").toString();
  }

  default void forEach(Unary<T, Void> body) {
    Seq<T> s = this;
    while(!s.isEmpty()) {
      body.call(s.first());
      s = s.rest();
    }
  }

  default void forEach(Binary<T, Boolean, Void> body) {
    Seq<T> s = this;
    while(!s.isEmpty()) {
      T e = s.first();
      s = s.rest();
      body.call(e, s.isEmpty());
    }
  }

  default T findFirst(Unary<T, Boolean> pred) {
    Seq<T> s = this;
    while(!s.isEmpty()) {
      T e = s.first();
      if (pred.call(e))
        return e;
      s = s.rest();
    }
    return null;
  }

  default boolean contains(Unary<T, Boolean> pred) {
    Seq<T> s = this;
    while(!s.isEmpty()) {
      T e = s.first();
      if (pred.call(e))
        return true;
      s = s.rest();
    }
    return false;
  }

  default long indexOf(Unary<T, Boolean> pred) {
    Seq<T> s = this;
    long i = 0;
    while(!s.isEmpty()) {
      T e = s.first();
      if (pred.call(e))
        return i;
      s = s.rest();
      i += 1;
    }
    return -1;
  }

  default <S> Seq<S> fmap(Unary<T, S> f) {
    return LazySeq.fmap(f, this);
  }
}
