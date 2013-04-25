package san.util.refs;

public class ShortRef {

  public static ShortRef initially(short value) {
    return new ShortRef(value);
  }

  private ShortRef(short value) {
    this.value = value;
  }

  public short value;

}
