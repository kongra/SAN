package edu.san.conc;

public class SynchronizedCounter {

  private long value;
  
  public SynchronizedCounter() {
    this(0L);
  }

  public SynchronizedCounter(long value) {
    this.value = value;
  }

  public synchronized void updateCounter(long delta) {
    value = Math.addExact(value, delta);
  }

  public synchronized long counterValue() {
    return value;
  }

}
