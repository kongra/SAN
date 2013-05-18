package san.jipp.util;

public class SeqTools {

  public interface BinaryFn<S> {

    S call(S lhs, S rhs);

  }

  public static <T> T reduce(Seq<T> coll, BinaryFn<T> f, T initial) {
    if (coll.isEmpty()) {
      return initial;
    }
    return reduce(coll.rest(), f, f.call(initial, coll.first()));
  }

}
