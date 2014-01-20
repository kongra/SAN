package san.messaging;

import java.util.Set;

class AsynchronousPump extends Pump {

  @Override
  protected void sendAll(Set<Target> targets, final Object message,
      final Object source) {
    for (final Target t : targets) {
      Runnable task = new Runnable() {
        @Override
        public void run() {
          t.onMessage(message, source);
        }
      };

      new Thread(task).start();
    }

  }

}
