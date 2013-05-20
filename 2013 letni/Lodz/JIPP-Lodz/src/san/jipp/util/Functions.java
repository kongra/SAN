package san.jipp.util;

public class Functions {

  public interface Binary<T> {

    T call(T lhs, T rhs);

  }

  public static <S> S reduce(Seq<S> coll, Binary<S> f, S initially) {
    if (coll.isEmpty()) {
      return initially;
    }
    return reduce(coll.rest(), f, f.call(initially, coll.first()));
  }

}
