import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

public class SynchronizedDeadlock<T> {

  private long callsCount;

  private T mostRecentCallValue;

  public synchronized long call(@NotNull Callable<T> body) {
    callsCount++;
    try {
      var value = body.call();
      mostRecentCallValue = value;
      return callsCount;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public synchronized long getCallsCount() {
    return callsCount;
  }

  public synchronized T getMostRecentCallValue() {
    return mostRecentCallValue;
  }
}
