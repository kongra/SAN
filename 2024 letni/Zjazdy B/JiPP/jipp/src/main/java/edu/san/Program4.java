// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

interface Program4 {

  // POLIMORFIZM UNIWERSALNY ("prawdziwy")
  // - P. INKLUZYJNY
  // - P. PARAMETRYCZNY

  // POLIMORFIZM INKLUZYJNY
  // A, B - typy danych (np. klasy, interfejsy)
  // A <: B - A jest podtypem B.
  // W ujęciu teorii mnogości każdy typ jest zbiorem.
  // W tym ujęciu A jest podzbiorem B. Występuje inkluzja zbiorów - A jest
  // zainkludowany przez B:
  // a należy do A, b należy do B
  // ----------------------------
  // a należy do B

  static class B {

    void m1() {
      System.out.println("Działa B::m1()");
    }

  }

  static class A extends B {

    @Override
    void m1() {
      // Metoda m1 w klasie A przesłania definicję m1 w klasie B.
      System.out.println("Działa A::m1()");
    }

  }

  static void foo(B b) {
    System.out.println("Działa foo z argumentem b=" + b);
    b.m1();
    // Występuje tutaj tzw. późne wiązanie (ang. late binding).
    // Wiązanie - proces kojarzenia symboli oznaczających różne elementy
    // programu (również procedur/metod) z ich wartościami (również adresami).
    // Późne - tzn. następujące w trakcie działania programu (w runtimie). Jest
    // również tzw. wczesne wiązanie, występujące w trakcie kompilacji, przed
    // uruchomieniem programu.

    // PROBLEMY:
    // - Patrząc na kod metody foo(B) nie wiemy, co DOKŁADNIE ona robi.
    // - Wywołanie b.m1() nie może podlegać operacji inline. Ten stan łagodzony
    // jest przez możliwości, których dostarcza nam JVM (dokładnie - kompilacja
    // Just-In-Time do kodu maszynowego).
  }

  static void main(String... args) {
    final var a = new A();
    final var b = new B();

    foo(b);
    foo(a);
    // Procedura foo jest prawdziwie polimorficzna i występuje w niej
    // polimorfizm inkluzyjny.
  }

  class C {}

  record D(int x, int y) {}

  abstract class E {
    abstract void e();
  }

  interface I {
    void i();
  }

  enum K {
    STATE_1, STATE_2;
  }

}
