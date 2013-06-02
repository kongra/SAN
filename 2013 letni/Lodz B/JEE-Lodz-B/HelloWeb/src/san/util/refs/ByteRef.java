package san.util.refs;

public class ByteRef {

  public static ByteRef initially(byte value) {
    return new ByteRef(value);
  }

  private ByteRef(byte value) {
    this.value = value;
  }

  public byte value;

}
