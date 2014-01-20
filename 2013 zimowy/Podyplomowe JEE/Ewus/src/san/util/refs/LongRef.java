package san.util.refs;

public class LongRef {

  public static LongRef initially(long value) {
    return new LongRef(value);
  }
  
  private LongRef(long value) {
    this.value = value;
  }

  public long value;
}