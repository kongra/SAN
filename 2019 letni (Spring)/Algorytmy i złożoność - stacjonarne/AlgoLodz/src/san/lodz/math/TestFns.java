package san.lodz.math;

public class TestFns {

  public static void main(String... args) {
//    Unary<Double, Double> f = new Unary<>() {
//      @Override
//      public Double call(Double x) {
//        return 2 * x + 3;
//      }
//    };

    Unary<Double, Double> f = x -> 2 * x + 3;
    System.out.println(f.call(10.0));

    Binary<Long, Double, Double> g =
        (n, x) -> x * n + 4;

    Nullary<Long> h = () -> {
      long n = 1;
      for (int i = 1; i <= 10; i++)
        n *= i;
      return n;
    };

    System.out.println(h.call());
  }

}
