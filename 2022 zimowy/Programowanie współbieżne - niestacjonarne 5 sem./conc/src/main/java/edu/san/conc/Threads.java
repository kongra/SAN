package edu.san.conc;

public final class Threads {

  public static void join(Thread t) {
    try {
      t.join();
    } catch (InterruptedException e) {
      e.printStackTrace(System.err);
    }
  }
  
  private Threads() {}

}
