package san.utils;

public class Test {

  public static void main(String... args) {
    Seq<String> s1 = LinkedSeq.of("abc", "xyz", "w");
    System.out.println(s1.isEmpty());
    Seq<Double> s2 = LinkedSeq.of(1.23, 3.14, 7.45);

    UnaryFn f0 = new UnaryFn() {
      @Override
      public double call(double x) {
        return x + 3;
      }
    };
    f0.call(2);

    UnaryFn f1 = (x) -> { return x + 3; };
    UnaryFn f2 = (x) -> x + 3;

    f2.call(1);

  }

}
