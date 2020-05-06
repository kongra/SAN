package san.jipp.funprog;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringTools {

  public static @NotNull List<Integer> transform(@NotNull StringWorker worker
      , @NotNull Collection<String> strs) {

    List<Integer> results = new ArrayList<>(strs.size());
    for (var s : strs) {
      results.add(worker.work(s));
    }
    return results;
  }

}
