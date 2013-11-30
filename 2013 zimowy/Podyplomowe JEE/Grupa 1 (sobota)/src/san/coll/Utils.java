package san.coll;

import san.coll.fn.Binary;
import san.coll.fn.NoArg;
import san.coll.fn.Unary;

public class Utils {

  public static <S> void doSeq(final ISeq<S> coll,
      final Unary<Void, S> body) {
    ISeq<S> s = coll;
    while (!s.isEmpty()) {
      body.call(s.first());
      s = s.rest();
    }
  }

  public static <S> void doSeq(final ISeq<S> coll, long maxSteps,
      final Unary<Void, S> body) {
    ISeq<S> s = coll;
    long count = 0;
    while (!s.isEmpty() && count != maxSteps) {
      body.call(s.first());
      s = s.rest();
      count += 1;
    }
  }

  public static ISeq<Long> integers(final long start) {
    return LazySeq.create(start, new NoArg<ISeq<Long>>() {
      @Override
      public ISeq<Long> call() {
        return integers(start + 1);
      }
    });
  }

  public static <R, S> ISeq<R> map(final Unary<R, S> f, final ISeq<S> coll) {
    if (coll.isEmpty()) {
      return LinkedSeq.empty();
    }

    return LazySeq.create(f.call(coll.first()), new NoArg<ISeq<R>>() {
      @Override
      public ISeq<R> call() {
        return map(f, coll.rest());
      }
    });
  }

  public static <R, S> R reduce(Binary<R, R, S> f, R start, ISeq<S> coll) {
    R value = start;
    while (!coll.isEmpty()) {
      value = f.call(value, coll.first());
      coll = coll.rest();
    }

    return value;
  }

  public static <S> ISeq<S> interpose(final S separator, final ISeq<S> coll) {
    // EMPTY ONE - KEEP IT
    if (coll.isEmpty()) {
      return LinkedSeq.empty();
    }

    // SINGLETON - KEEP IT
    if (coll.rest().isEmpty()) {
      return coll;
    }

    return LazySeq.create(coll.first(), new NoArg<ISeq<S>>() {
      @Override
      public ISeq<S> call() {
        return LazySeq.create(separator, new NoArg<ISeq<S>>() {
          @Override
          public ISeq<S> call() {
            return interpose(separator, coll.rest());
          }
        });
      }
    });
  }

  @SuppressWarnings("unchecked")
  public static <S> String toString(ISeq<S> coll) {
    final StringBuilder buf = new StringBuilder("(");

    Utils.doSeq(((ISeq<Object>) coll).interpose(", "),
      new Unary<Void, Object>() {
        @Override
        public Void call(Object element) {
          buf.append(element);
          return null;
        }
      });

    return buf.append(")").toString();
  }

  public static <S> ISeq<S> repeat(final S obj) {
    return LazySeq.create(obj, new NoArg<ISeq<S>>() {
      @Override
      public ISeq<S> call() {
        return repeat(obj);
      }
    });
  }

  public static <S> ISeq<S> repeatedly(final NoArg<S> f) {
    return LazySeq.create(f.call(), new NoArg<ISeq<S>>() {
      @Override
      public ISeq<S> call() {
        return repeatedly(f);
      }
    });
  }

  private Utils() {
    ;
  }

}
