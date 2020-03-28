public class Zoo {

  public static void main(String... args) {
    Elephant e1 = new Elephant();
    Zebra z1    = new Zebra();
    Animal v1   = new Viper();

    feed("Grass", e1);
    feed("Grass", z1);
    feed("Grass", e1, z1);
    feed("Grass", e1, z1, e1, z1);
    feed("Rat", v1);

    v1.sleep();

    // e1.eat("Grass");
    // z1.eat("Grass");
    // feed("Grass", e1);
    // feed("Grass", z1);
  }

  // * feed jest polimorficzna
  // * jest to polimorfizm uniwersalny
  // * rodzaj polimorfizmu: INKLUZYJNY
  private static void feed(Object food
      , Animal... animals) {

    for (var animal : animals) {
      // Późne wiązanie (late binding):
      animal.eat(food);
    }
  }

//  private static void feed(Object food, Elephant e1) {
//    e1.eat(food);
//  }
//
//  private static void feed(Object food, Zebra z1) {
//    z1.eat(food);
//  }
}
