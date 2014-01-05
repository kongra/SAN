package san.lect04;

import java.util.concurrent.atomic.AtomicBoolean;

public class Stoppable implements Runnable {

  private final Runnable task;

  private final AtomicBoolean stopped = new AtomicBoolean(false);

  public Stoppable(Runnable task) {
    this.task = task;
  }

  @Override
  public void run() {
    while (!isStopped()) {
      task.run();
    }
  }

  public void stop() {
    stopped.set(true);
  }

  public boolean isStopped() {
    return stopped.get();
  }

}
