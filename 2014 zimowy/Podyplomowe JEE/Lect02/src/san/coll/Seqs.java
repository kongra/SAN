package san.coll;

import san.math.Bfn;
import san.math.Delay;
import san.math.Nfn;
import san.math.Ufn;

public class Seqs {

  public static <T> ISeq<T> interpose(final ISeq<T> s, final T separator) {
    if (s.isEmpty()) {
      return s;
    }
    final T x = s.first();
    ISeq<T> xs = s.rest();
    if (xs.isEmpty()) {
      return s;
    }
    return interpose(xs, separator).cons(separator).cons(x);
  }

  @SuppressWarnings("unchecked")
  public static <T, S> ISeq<S> map(final Ufn<T, S> f, ISeq<T> s) {
    if (s.isEmpty()) {
      return Nil.nil;
    }
    final T x = s.first();
    final ISeq<T> xs = s.rest();
    return map(f, xs).cons(f.call(x));
  }

  public static <T> T reduce(final Bfn<T, T, T> f, T z, ISeq<T> s) {
    if (s.isEmpty()) {
      return z;
    }
    final T x = s.first();
    final ISeq<T> xs = s.rest();
    return reduce(f, f.call(z, x), xs);
  }

  public static <T> ISeq<T> lcons(T x, Nfn<ISeq<T>> expr) {
    return new LazySeq<T>(x, new Delay<ISeq<T>>(expr));
  }

  public static <T> ISeq<T> iterate(final Ufn<T, T> f, final T x) {
    return lcons(x, new Nfn<ISeq<T>>() {
      @Override
      public ISeq<T> call() {
        return iterate(f, f.call(x));
      }
    });
  }

  public static <T> ISeq<T> take(final long n, ISeq<T> s) {
    if (n == 0 || s.isEmpty()) {
      return Nil.nil;
    }
    final T x = s.first();
    final ISeq<T> xs = s.rest();
    return lcons(x, new Nfn<ISeq<T>>() {
      @Override
      public ISeq<T> call() {
        return take(n - 1, xs);
      }
    });
  }

  public static final Ufn<Long, Long> longInc = new Ufn<Long, Long>() {
    @Override
    public Long call(Long arg) {
      return arg + 1;
    }
  };

  public static ISeq<Long> naturals() {
    return iterate(longInc, 0L);
  }

  public static <T> String toString(ISeq s) {
    StringBuilder buf = new StringBuilder("(");
    ISeq current = interpose(s, ",");
    while (!current.isEmpty()) {
      buf.append(current.first());
      current = current.rest();
    }
    return buf.append(")").toString();
  }

  private Seqs() {
  }

}
