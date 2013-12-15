package san.util.refs;

public class CharRef {

  public static CharRef initially(char value) {
    return new CharRef(value);
  }
  
  private CharRef(char value) {
    this.value = value;
  }

  public char value;
  
}
