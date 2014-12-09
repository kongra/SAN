package san.conc.msg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MessagePump {

  private final List<Listener> listeners = new LinkedList<>();

  public synchronized void register(Listener l) {
    listeners.add(l);
  }

  public synchronized boolean unregister(Listener l) {
    return listeners.remove(l);
  }

  public void fire(Object msg) {
    final List<Listener> copy;
    synchronized (this) {
      copy = new ArrayList<>(this.listeners);
    }
    for (Listener l : copy) {
      l.onMessage(msg);
    }
  }
  
}
