package multi;

public class SeqGen {

  private long value;

  public synchronized long gen() {
    return value++;
  }

  public synchronized long value() {
    return value;
  }

}
