package edu.san;

public class Counter {

  // private Semaphore s1 = new Semaphore(1, true);
  // private Object m1 = new Object();

  private long value;

  // public void inc() {
  // // 1. mov rax, MEM[value]
  // // 2. add rax, 1
  // // 3. mov MEM[value], rbx
  //
  // // t1, t2
  // // t1, 1. (20)
  // // t2, 1. (20)
  // // t1, 2. (21)
  // // t2, 2. (21)
  // // t1, 3. 21
  // // t2, 3. 21
  //
  // try {
  // s1.acquire();
  // value++;
  // } catch (InterruptedException e) {
  // e.printStackTrace();
  // } finally {
  // s1.release();
  // }
  // }

  //  public void inc() {
  //    synchronized (this) {
  //      value++;
  //    }
  //  }
  //
  //  public long value() {
  //    synchronized (this) {
  //      return value;
  //    }
  //  }

  public synchronized void inc() {
    value++;
  }

  public synchronized long value() {
    return value;
  }
}
