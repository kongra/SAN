package san.jee.lect03;

public class TestBox {

  public static void main(String[] args) {
    // Box x = new Box(1);
    // Box y = new Box(2);
    // Box z = add(x, y);

    Box<Integer> x = new Box<>(1);
    Box<Integer> y = new Box<>(2);

    Box<Short> s = new Box<>((short) 3);
    Box<Float> f = new Box<>(3.14f);

    Box<Double> z = add(x, y);
    Box<Double> w = add(s, f);

    System.out.println(z.get());
    System.out.println(w.get());

    Box<Long> q = add(f, x);
    System.out.println(q.get().longValue());
  }

  @SuppressWarnings("unchecked") // STRASZNY BUG
  private static <S extends Number> Box<S> add(Box<? extends Number> x,
      Box<? extends Number> y) {

    double result = x.get().doubleValue() + y.get().doubleValue();
    return (Box<S>) new Box<>(result);
  }

  // private static Box add(Box x, Box y) {
  // System.out.println(x.get().getClass());
  //
  // Double valX = (Double) x.get();
  // Double valY = (Double) y.get();
  //
  // // double d = valX + valY;
  //
  // // return new Box(valX + valY);
  //
  // return new Box(Double.valueOf(valX.doubleValue() + valY.doubleValue()));
  // }

}
