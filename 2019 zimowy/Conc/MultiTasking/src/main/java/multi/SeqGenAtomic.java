package multi;

import java.util.concurrent.atomic.AtomicLong;

public final class SeqGenAtomic {

  private final AtomicLong value = new AtomicLong();

  public long gen() {
    return value.incrementAndGet();
  }

  public long value() {
    return value.get();
  }

}
