// © 2023 Konrad Grzanek <kongra@gmail.com>
package edu.san.jipp.animals;

public class ZooKeeper {

  public ZooKeeper(String firstName) {}

  // Q: Czy poniższa metoda jest polimorficzna?
  // A: TAK
  // Q: Jaki to jest rodzaj polimorfizmu?
  // A: Jest to POLIMORFIZM INKLUZYJNY
  // Q: Jaki mechanizm implementacyjny tutaj działa?
  public void feed(String food, Animal... animals) {
    for (final var animal : animals) {
      // A: Późne wiązanie (eng. late-binding)
      animal.eat(food);

      // Q: Co to jest wiązanie?
      // A: Proces ustalania, jaki obiekt odpowiada danemu symbolowi w kodzie
      // programu.
    }
  }

}
