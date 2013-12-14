package san.jee.lect04;

import san.concurrent.Threads;

public class Test01 {

  private static boolean running;

  public synchronized static void setRunning(boolean running) {
    Test01.running = running;
  }

  public static synchronized boolean isRunning() {
    return Test01.running;
  }

  static final Object starter = new Object();

  static class MyTask implements Runnable {
    @Override
    public void run() {
      synchronized (starter) {
        Threads.wait(starter);
      }

      while (isRunning()) {
        Threads.println("Działam");
        Threads.sleepRandomly(1000);
      }

      Threads.println("Zwalniam zasoby");
    }
  }

  public static void main(String[] args) {
    // Thread t1 = new MyTask();
    // t1.start();

    Runnable task = new MyTask();
    setRunning(true);
    Threads.futures(5, "MyTask", task);

    Threads.println("Idę spać 1");
    Threads.sleep(3000);

    synchronized (starter) {
      starter.notifyAll();
    }
    
    Threads.println("Idę spać 2");
    Threads.sleep(3000);
    
    setRunning(false);

    Threads.println("KONIEC");

  }

}
