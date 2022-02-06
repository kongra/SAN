public class Goo extends Foo {

  public void goo() {
    System.out.println("Działa Goo::goo");
  }

  @Override
  public void foo() {
    super.foo(); // ang. super od supertype (nadtyp)
    System.out.println("Działa Goo::foo, przesłonięta");
  }

  // Goo <: Foo
  // Goo IS-A Foo
  // Goo ROZSZERZA Foo
  // Goo JEST ROZSZERZENIEM Foo

  // Foo i Goo są  TYPAMI
  // W takim razie w ujęciu TYPU pochodzącym z teorii mnogości
  // Foo i Goo są ZBIORAMI
  // Goo JEST PODZBIOREM Foo
  // A więc Goo JEST MNIEJSZY niż Foo

  // Każdy ZBIÓR a więc i TYP posiada tzw. funkcję
  // charakterystyczną,
  // (np. zbiór A, ma funkcję ch. fA) która dla każdego
  // obiektu udziela odpowiedzi na pytanie, czy
  // obiekt ten należy do zbioru (A).
  // Jeżeli mamy zbiór N = 0,1,2,3,4,... to
  // fN(0) => True, fN(100) => True, fN(-1) => False

  // Im zbiór jest bardziej ogólny, tym f-cja charakterystyczna
  // jest prostsza, mniejsza, bardziej 'liberalna'
  // Im zbiór bardziej konkretny, szczegółowy, tym funkcja
  // ta jest bardziej skomplikowana, rozbudowana.

  // Funkcja charakterystyczna dla Goo jest bardziej
  // rozbudowana niż funkcja charakterystyczna dla Foo.

  // DZIEDZICZENIE (ang. INHERITANCE)
  // Anomalie i patologie
  // class Neck {}
  // class Human extends Neck {}
  //
  // class Human {
  //   Neck neck;
  // }

  // ZASADA SUBSTYTUTYWNOŚCI BARBARY LISKOV
  // Niech A i B będą zbiorami (typami).
  // B <: A, tzn. B jest podzbiorem (podtypem) A
  // jeżeli x należy do B, to x należy do A.
  // Wszędzie tam, gdzie jest symbol s : A, można
  // podstawić obiekt typu B.

  // PRZESŁONIĘCIE (ang. OVERRIDING)

  // @Override - przykład ADNOTACJI (ang. annotation)
}
