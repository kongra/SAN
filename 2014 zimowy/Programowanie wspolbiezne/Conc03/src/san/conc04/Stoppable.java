package san.conc04;

public abstract class Stoppable implements Runnable {

  private boolean stopped;

  public synchronized boolean isStopped() {
    return stopped;
  }

  public synchronized void stop() {
    stopped = true;
  }

  public boolean isRunning() {
    return !isStopped();
  }
}
