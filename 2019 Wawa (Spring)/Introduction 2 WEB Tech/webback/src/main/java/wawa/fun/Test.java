package wawa.fun;

public class Test {

  public static void main(String... args) {
    Unary<Integer, Integer> inc = (x) -> {
      // System.out.println("inc running with argument " + x);
      return x + 1;
    };

    System.out.println(inc.call(5));
  }

}
