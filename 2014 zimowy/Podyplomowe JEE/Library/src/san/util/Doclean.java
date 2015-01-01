package san.util;

import java.util.LinkedList;
import java.util.List;

public class Doclean {

  private static final DynVar<Doclean> context = DynVar.initially(null);

  /**
   * Runs the body of code wihin a new established cleanup context. Cleans the
   * context after doing the job.
   * 
   * @param body
   */
  public static void runNew(Runnable body) {
    Doclean doclean = new Doclean();
    try {
      context.binding(doclean, body);
    }
    finally {
      doclean.clear();
    }
  }

  /**
   * Runs the body of code within a cleanup context. If the context is null,
   * establishes a new one.
   * 
   * @param body
   */
  public static void run(Runnable body) {
    Doclean doclean = context.value();
    if (doclean != null) {
      body.run();
    }
    else {
      runNew(body);
    }
  }

  public static Doclean assertDoclean() {
    Doclean doclean = context.value();
    if (doclean == null) {
      throw new RuntimeException("Doclean is absent!!!");
    }
    return doclean;
  }

  public void register(Runnable cleanupBody) {
    bodies.add(0, cleanupBody);
  }

  private void clear() {
    for (Runnable body : bodies) {
      try {
        body.run();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private final List<Runnable> bodies = new LinkedList<Runnable>();

  private Doclean() {
    ;
  }

}
