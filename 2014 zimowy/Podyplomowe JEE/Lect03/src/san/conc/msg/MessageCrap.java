package san.conc.msg;

import java.util.LinkedList;
import java.util.List;

public class MessageCrap {

  private final List<Listener> listeners = new LinkedList<>();

  public synchronized void register(Listener l) {
    listeners.add(l);
  }

  public synchronized boolean unregister(Listener l) {
    return listeners.remove(l);
  }

  public synchronized void fire(Object msg) {    
    for (Listener l : listeners) {
      l.onMessage(msg);
    }
  }
}
