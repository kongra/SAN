package san.conc;

public class Counter {

  private long value;

//  public long increment() {
//    synchronized (this) {
//      this.value += 1;
//      return this.value;
//    }
//  }

  public synchronized long increment() {
    this.value += 1;
    return this.value;
  }

  public synchronized void reset() {
    this.value = 0;
  }

  public synchronized long get() {
    return this.value;
  }

}
