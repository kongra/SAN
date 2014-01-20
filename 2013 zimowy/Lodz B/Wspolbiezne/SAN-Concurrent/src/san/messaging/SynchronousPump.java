package san.messaging;

import java.util.Set;

class SynchronousPump extends Pump {

  @Override
  protected void sendAll(Set<Target> targets, Object message, Object source) {
    for (Target t : targets) {
      t.onMessage(message, source);
    }
  }

}
