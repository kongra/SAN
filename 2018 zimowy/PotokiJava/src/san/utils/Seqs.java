package san.utils;

import san.conc.Delay;
import san.conc.Unary;

public class Seqs {

  public static <T> Seq<T> iterate(Unary<T, T> f, T start) {
    return new LazySeq<>(start, new Delay<>(() -> iterate(f, f.call(start))));
  }

  public static Seq<Long> naturals() {
    return iterate((n) -> n + 1L, 0L);
  }

  public static <T, S> Seq<S> map(Unary<T, S> f, Seq<T> seq) {
    if (seq.isEmpty()) {
      return Nil.nil();
    }
    return new LazySeq<>(f.call(seq.first()), new Delay<>(() -> map(f, seq.rest())));
  }

  public static <T, S> T reduce(Binary<T, S, T> f, T start, Seq<S> seq) {
    if (seq.isEmpty()) return start;
    return reduce(f, f.call(start, seq.first()), seq.rest());
  }

  public static <T> Seq<T> take(long n, Seq<T> seq) {
    if (seq.isEmpty()) return seq;
    if (n == 0) return Nil.nil();
    return new LazySeq<>(seq.first(), new Delay<>(() -> take(n - 1, seq.rest())));
  }

  private Seqs() {

  }

}
