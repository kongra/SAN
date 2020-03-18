package san.jipp.types;

import org.jetbrains.annotations.NotNull;

public class Zoo {

  // * Jest polimorficzna
  // * food pozwala przekazać obiekt różnych typów
  // * animals pozwala przekazać obiekty różnych typów
  public void feed(Object food
      , @NotNull Animal animal
      , @NotNull Animal... animals) {

    animal.eat(food);
    for (Animal a : animals) {
      a.eat(food);
    }
  }

  public void feedArray(Object food
      , @NotNull Animal[] animals) {
    for (Animal a : animals) {
      a.eat(food);
    }
  }

  // nie jest polimorficzna
  public static void main(String... args) {
    testFeed();
    testFeedArray();

    Animal a1 = new Elephant();
  }

  private static void testFeedArray() {
    var zoo = new Zoo();
    var food1 = "Trawa";
    var elephant1 = new Elephant();
    var camel1    = new Camel();

    zoo.feedArray(food1, new Camel[] {camel1});
    zoo.feedArray(food1, new Elephant[] {elephant1});
    zoo.feedArray(food1, new Animal[] {elephant1, camel1});
  }

  private static void testFeed() {
    var zoo = new Zoo();
    var food1 = "Trawa";
    var elephant1 = new Elephant();
    var camel1    = new Camel();

    zoo.feed("Liście", camel1, elephant1);
    zoo.feed(food1, camel1, elephant1);
    zoo.feed(3, camel1, elephant1);
  }
}
