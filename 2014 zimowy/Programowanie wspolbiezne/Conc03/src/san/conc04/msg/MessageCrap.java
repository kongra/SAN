package san.conc04.msg;

import java.util.LinkedList;
import java.util.List;

public class MessageCrap {

  public synchronized void register(Listener l) {
    listeners.add(l);
  }
  
  public synchronized void unregister(Listener l) {
    listeners.remove(l);
  }
  
  public synchronized void fire(Object msg) {
    for(Listener l : listeners) {
      l.onMessage(msg);
    }
  }
  
  private final List<Listener> listeners = new LinkedList<>();
  
}
