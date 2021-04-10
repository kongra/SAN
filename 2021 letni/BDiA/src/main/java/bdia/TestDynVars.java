package bdia;

public class TestDynVars {

  public static final DynVar<Integer> x = DynVar.initially(5);

  static void foo() {
    System.out.println(x.value());
  }

  static void foo1() {
    foo();
    x.binding(4, () -> {
      foo();
      {
        x.binding(6, () -> {
          foo();
        });
        foo();
      }
    });
    foo();
  }

  public static void main(String... args) {
    foo1();
  }

}
