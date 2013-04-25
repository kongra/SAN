package san.util.refs;

public class IntRef {

  public static IntRef initially(int value) {
    return new IntRef(value);
  }
  
  private IntRef(int value) {
    this.value = value;
  }

  public int value;
}