package edu.san.conc;

public final class Threads {

  public static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
  
  private Threads() {}
}
