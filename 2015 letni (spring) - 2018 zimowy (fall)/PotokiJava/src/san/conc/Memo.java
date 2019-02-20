package san.conc;

import san.utils.Maybe;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Memo<T, S> implements Unary<T, S> {

  private final Unary<T, S> f;

  private final Map<T, Maybe<S>> cache = Collections.synchronizedMap(new HashMap<>());


  public Memo(Unary<T, S> f) {
    this.f = f;
  }

  @Override
  public S call(T arg) {
    Maybe<S> s = cache.get(arg);
    if (!s.isNothing()) {
      return s.value();
    }

    s = Maybe.just(f.call(arg));
    cache.put(arg, s);
    return s.value();
  }

}
