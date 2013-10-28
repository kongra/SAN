package san.concurrent;

public abstract class Stoppable implements Runnable {

  private boolean stopped;

  public synchronized void stop() {
    this.stopped = true;
  }

  public synchronized boolean isStopped() {
    return this.stopped;
  }

}
