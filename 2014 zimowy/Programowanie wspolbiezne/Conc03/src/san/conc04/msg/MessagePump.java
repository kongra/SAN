package san.conc04.msg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MessagePump {

  public synchronized void register(Listener l) {
    listeners.add(l);
  }

  public synchronized void unregister(Listener l) {
    listeners.remove(l);
  }

  public void fire(Object msg) {
    final List<Listener> copy;
    synchronized (this) {
      copy = new ArrayList<>(listeners);
    }
    for (Listener l : copy) {
      l.onMessage(msg);
    }
  }

  private final List<Listener> listeners = new LinkedList<>();

}
