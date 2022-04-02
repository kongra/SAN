import java.util.*;

class Polinclus {

  static class A {
    void foo() {
      System.out.println("Działa A::foo");
    }
  }

  static class B extends A {
    @Override
    void foo() {
      System.out.println("Działa B::foo");
    }
  }

  // runner jest procedurą (metodą) prawdziwie polimorficzną
  // Polimorfizm tutaj obecny jest INKLUZYJNY, ponieważ
  // runner akceptuje argumenty typu A oraz innych typów, będących
  // podtypami A (np. B).
  static void runner(A obj) {
    System.out.println("Działa runner z argumentem " + obj);

    // Tutaj występuje tzw. późne wiązanie (late binding), tj. ustalenie
    // rzeczywistego ciała procedury foo, które zostanie wykonane.
    // Jest to zależne od faktycznego typu obiektu obj - typ ten
    // znany jest dopiero w trakcie wykonania procedury, dlatego
    // uzasadnione jest użycie określenia "późne" lub "dynamiczne"
    // wiązanie (w odróżnieniu od wczesnego/statycznego wiązania).
    obj.foo();
  }

  static void multiRunner(Collection<A> objs) {
    System.out.println("Działa multiRunner");
    for (var obj : objs) {
      obj.foo(); // late-binding
    }
  }

  public static void main(String... args) {
    var obj1 = new A();
    var obj2 = new B();

    // runner(obj1);
    // runner(obj2);

    var objs1 = List.of(obj1, obj2);
    var objs2 = Set.of(obj1, obj2);
    multiRunner(objs1);
    multiRunner(objs2);
  }

}
