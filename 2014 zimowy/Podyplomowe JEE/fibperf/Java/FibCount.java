package san.math;

public class FibCount {

  public final long value;

  public final long count;

  public FibCount(long value, long count) {
    this.value = value;
    this.count = count;
  }

  @Override
  public String toString() {
    return "FibCount [value=" + value + ", count=" + count + "]";
  }

}
