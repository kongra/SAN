package san.jee.lect05;

public class Lect05 {

  public static void main(String[] args) {
    // Zadanie. Zrealizuj funkcjonalność z przykładu STM z wykorzystaniem
    // transakcyjnego silnika relacyjnych baz danych. Założenia:
    // 1. System przechowuje informacje o pracownikach firmy a nie po prostu o
    // dowolnych obiektach.
    // 2. Z przyczyn obiektywnych rezygnujemy z odraczania.

    try (UserStore store = new UserStore()) {
      store.add("Jan", "Kowalski");
      store.add("Grzegorz", "Nowak");

      System.out.println(store.findByName("Grzegorz"));
    }

  }

}
