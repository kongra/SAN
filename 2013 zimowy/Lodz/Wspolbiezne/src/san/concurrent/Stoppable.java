package san.concurrent;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Stoppable implements Runnable {

  private final AtomicBoolean stopped = new AtomicBoolean(false);

  public boolean isStopped() {
    return stopped.get();
  }

  public void stop() {
    stopped.set(true);
  }

}
