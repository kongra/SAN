package san.messaging;

import java.util.Set;

class CummulativeAsynchronousPump extends Pump {

  @Override
  protected void sendAll(final Set<Target> targets, final Object message,
      final Object source) {
    Runnable task = new Runnable() {
      @Override
      public void run() {
        for (Target t : targets) {
          t.onMessage(message, source);
        }
      }
    };

    new Thread(task).start();
  }

}
