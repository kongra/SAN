package san.conc;

public abstract class Stoppable implements Runnable {

  private boolean stopped;

  public synchronized void stop() {
    stopped = true;
  }

  public synchronized boolean isStopped() {
    return stopped;
  }

}
