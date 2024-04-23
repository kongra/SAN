// © 2024 Konrad Grzanek <kongra@gmail.com>
package edu.san;

interface PolimorfizmUniwersalny {

  // POLIMORFIZM UNIWERSALNY - jest to PRAWDZIWY polimorfizm.
  // Są dwa warianty tego polimorfizmu:
  // * polimorfizm INKLUZYJNY
  // * polimorfizm PARAMETRYCZNY

  // POLIMORFIZM INKLUZYJNY

  static final boolean LOG = true;

  static void logDebug(Object message) {
    if (LOG) {
      var str = message.toString();
      System.out.println(str + " : " + message.getClass().getName());
    }
  }

  static class Messageable {

    String asMessage() {
      return "The default Messageable message";
    }

  }

  static class Event extends Messageable {

    final int code;

    @Override
    String asMessage() {
      return "The Event.message is " + code;
    }

    Event(int code) {
      this.code = code;
    }

  }

  static void logMessage(Messageable messageable) {
    if (LOG) {
      var message = messageable.asMessage(); // late-binding
      System.out.println(message + " : " + messageable.getClass().getName());
    }
  }

  static void main(String... args) {
    logDebug(4L);
    logDebug(3.14159);
    logDebug("Some output");

    record Tuple(int n, double x) {}
    logDebug(new Tuple(5, 9.245));
    // Metoda statyczna logDebug JEST polimorficzna!

    var m1 = new Messageable();
    var m2 = new Event(3);

    logMessage(m1);
    logMessage(m2);
    // Metoda logMessage jest polimorficzna!

    // We wszystkich miejscach programu, w których oczekujemy użycia typu
    // Messageable, można używać Event, ponieważ Event jest podtypem
    // Messageable.

    // Występuje tutaj INKLUZJA zbiorów. Dokładniej, zbiór Event jest podzbiorem
    // Messageable, więc jest zainkludowany we wnętrzu Messageable.

    // W wyrażeniu messageable.asMessage() dochodzi do 'wyboru' wariantu metody
    // asMessage, która zostanie tam docelowo użyta. W naszym przypadku mamy
    // (teraz, nie wiadomo, co będzie w przyszłości) Messageable.asMessage oraz
    // Event.asMessage. O tym, która z nich zadziała w trakcie wywołania
    // logMessage zależy od faktycznego typu argumentu messageable. Mówimy tutaj o:
    // * późnym wiązaniu (ang. late binding)
    // * wywołaniu metody wirtualnej

  }

}
