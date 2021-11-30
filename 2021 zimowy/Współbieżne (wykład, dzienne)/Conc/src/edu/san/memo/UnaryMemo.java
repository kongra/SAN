package edu.san.memo;

import java.util.HashMap;
import java.util.Map;

public final class UnaryMemo<S, T> implements Unary<S, T> {

  public static <S, T> UnaryMemo<S, T> make(Unary<S, T> f) {
    return new UnaryMemo<S, T>(f);
  }

  private final Unary<S, T> f;

  private final Map<S, T> cache = new HashMap<>();

  private UnaryMemo(Unary<S, T> f) {
    super();
    this.f = f;
  }

  @Override
  public T apply(S obj) {
    var v = cache.getOrDefault(obj, null);
    if (v == null) {
      v = f.apply(obj);
      cache.put(obj, v);
    }

    return v;
  }

}
