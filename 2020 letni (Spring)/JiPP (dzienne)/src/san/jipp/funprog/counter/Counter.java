package san.jipp.funprog.counter;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicLong;

@FunctionalInterface
public interface Counter {

  long next();

  @Contract(pure = true)
  static @NotNull Counter make(long start) {
    final AtomicLong value = new AtomicLong(start - 1);
    return value::incrementAndGet;
  }

}
