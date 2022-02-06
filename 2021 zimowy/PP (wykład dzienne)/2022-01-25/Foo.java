public class Foo /* extends Object */ {

  public void foo() {
    System.out.println("Działa Foo::foo");
  }

  public static void main(String[] args) {
    // Foo f1 = new Foo();
    // f1.foo();

    // Goo g1 = new Goo();
    // g1.foo();
    // g1.goo()

    Foo f2 = new Goo();
    f2.foo();
  }

  // RELACJA 'JEST RODZAJEM', IS-A, 'JEST PODTYPEM', <:
  // * pies jest rodzajem ssaka
  //   DOG    IS-A (IS-AN) MAMMAL, DOG    <: MAMMAL
  // * ssak jest rodzajem zwierzęcia
  //   MAMMAL IS-AN        ANIMAL, MAMMAL <: ANIMAL
  // * relacja <: jest przechodnia (ang. transitive) tzn.
  //   A <: B, B <: C => A <: C
  // * byte <: short <: int <: long <: float <: double
  //            char <: int <: long <: float <: double

  // Można zdefiniować klasę Goo, która będzie podtypem Foo
  // Goo <: Foo

  // Q: Z CZEGO DZIEDZICZY Foo?
  // A: W SPOSÓB NIEJAWNY DZIEDZICZY z klasy java.lang.Object
}
