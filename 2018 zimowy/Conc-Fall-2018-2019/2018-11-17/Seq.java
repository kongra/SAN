import java.util.concurrent.atomic.*;

public class Seq {

  // private long value;

  // public synchronized long next() {
  //   return this.value++;
  // }

  private final AtomicLong value = new AtomicLong(-1);

  public long next() {
    return this.value.incrementAndGet();
  }

  public static void main(String[] args) {
    Seq s = new Seq();

    System.out.println(s.next());
    System.out.println(s.next());
    System.out.println(s.next());
    System.out.println(s.next());
  }

}
