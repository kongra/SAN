import java.util.*;

class Sender {

  static <T extends Sendable> void send(List<T> list) {
    for (var e : list) {
      e.send();
    }
  }

}
