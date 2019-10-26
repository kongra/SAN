import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;


public class SharedDeadlock2 {

  private static int count = 0;

  private static Object monitor = new Object();

  static String myTrain;

  @NotNull
  @Contract("_ -> new")
  static Thread makeTrain(String s) {
    return new Thread(() -> {
      synchronized (monitor) {
        synchronized (monitor) {
          count += s.length();
          System.out.println("Obecnie count jest równy " + count);
        }
      }
    });
  }

  public static void main(String... args) {
    makeTrain("E-09").start();
    makeTrain(myTrain).start();
    makeTrain("E-08").start();

    // Monitory są RE-ENTRANT

    System.out.println("Koniec.");
  }

}
