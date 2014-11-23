package san.coll;

import san.math.Ufn;

public abstract class Cons implements Ufn<Integer, Object> {

  public static Cons cons(final Object first, final Cons rest) {
    return new Cons() {
      @Override
      public Object call(Integer arg) {
        if (arg == 0) {
          return first;
        }
        return rest;
      }
    };
  }

  public static Object first(Cons c) {
    return c.call(0);
  }

  public static Cons rest(Cons c) {
    return (Cons) c.call(1);
  }

}
